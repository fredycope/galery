package com.example.galery.data.models.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.data.utils.OnClickList
import com.bumptech.glide.Glide
import com.example.galery.R
import com.example.galery.data.models.ResponsePhotos
import com.example.galery.databinding.MainItemBinding
import java.util.*
import kotlin.collections.ArrayList

class ImagesAdapter(val onClickList: OnClickList, val mainFr: Boolean) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {


    private val listDataResult: MutableList<ResponsePhotos> = ArrayList()
    private val listDataResult2: MutableList<ResponsePhotos> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.main_item, parent, false)
        return ViewHolder(view)
    }

    fun addData(list:List<ResponsePhotos>){
        this.listDataResult.clear()
        this.listDataResult.addAll(list)
        listDataResult2.addAll(listDataResult)
        notifyDataSetChanged()
    }

    fun filter(text: String){
        listDataResult.clear()
        if(text.length == 0){
            listDataResult.addAll(listDataResult2)
        }else{
            for (value: ResponsePhotos in listDataResult2){
                if(value.user.username != null || value.user.name != null){
                    if(value.user.username.toLowerCase(Locale.getDefault()).contains(text)
                        ||value.user.name.toLowerCase(Locale.getDefault()).contains(text)){
                        listDataResult.add(value)
                    }
                }

            }
        }

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        (holder as ViewHolder).onBind(listDataResult[position])
    }

    override fun getItemCount() = listDataResult!!.size

    inner class ViewHolder(val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(item: ResponsePhotos) = with(itemView) {
            val listPhoto = dataBinding as MainItemBinding
            Glide.with(listPhoto.ivPoster).load(item.urls.full).into(listPhoto.ivPoster)
            Glide.with(listPhoto.ivProfile).load(item.user.profile_image.medium).into(listPhoto.ivProfile)

            listPhoto.tvLikes.text = item.likes.toString()
            listPhoto.tvUser.text = item.user.name

            if(!mainFr){
                listPhoto.ibtnFav.setBackgroundResource(R.drawable.ic_delete)
            }

            listPhoto.ivPoster.setOnClickListener {
                onClickList.goToFragment(item,it)
            }

            listPhoto.ibtnFav.setOnClickListener {
                onClickList.clickFavorite(item)
            }

        }
    }
}