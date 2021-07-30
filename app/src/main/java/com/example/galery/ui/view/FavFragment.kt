package com.example.galery.ui.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.backbase.assignment.data.utils.Nav
import com.backbase.assignment.data.utils.OnClickList
import com.example.galery.R
import com.example.galery.data.models.ResponsePhotos
import com.example.galery.data.models.adapters.ImagesAdapter
import com.example.galery.databinding.FragmentFavBinding
import com.example.galery.ui.viewmodel.FavViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavFragment : Fragment() , OnClickList {
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null
    lateinit var binding: FragmentFavBinding
    lateinit var adapter: ImagesAdapter
    private val viewModel: FavViewModel by viewModels()

    @Inject
    lateinit var nav: Nav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.getFav()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_fav, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFav.layoutManager = GridLayoutManager(requireContext(), 2).also {
            it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position % 3 == 0)
                        2
                    else
                        1
                }
            }
        }
        adapter = ImagesAdapter(this, false)
        binding.rvFav.adapter = adapter


        viewModel.getFav.observe(viewLifecycleOwner, Observer {
            val list = ArrayList<ResponsePhotos>()
            if (it.isEmpty()){
                binding.tvFavorite.visibility = View.VISIBLE
                binding.rvFav.visibility = View.GONE
            }else{
                binding.tvFavorite.visibility = View.GONE
                binding.rvFav.visibility = View.VISIBLE
                it.forEach {
                    val gson = Gson()
                    list.add(gson.fromJson(it.description,ResponsePhotos::class.java))
                }
                initRecyclerView(list)
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater: MenuInflater = requireActivity().menuInflater
        inflater.inflate(R.menu.menu_bar, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(requireActivity()!!.componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    Log.i("onQueryTextChange", newText)
                    adapter.filter(newText)
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.i("onQueryTextSubmit", query)
                    return false
                }
            }
            searchView!!.setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        searchView?.setOnQueryTextListener(queryTextListener);
        return true
    }

    fun initRecyclerView(list: List<ResponsePhotos>){
        adapter.addData(list)
    }

    override fun goToFragment(result: Any, view: View) {
        val res = (result) as ResponsePhotos
        val bundle = bundleOf("response" to res)
        nav.gotoFragment(view,R.id.action_favFragment_to_detailsFragment,bundle)
    }

    override fun clickFavorite(result: Any) {
        val delete = result as ResponsePhotos
        viewModel.deleteFav(delete.id)
        Toast.makeText(requireContext(),"Favorito eliminado",Toast.LENGTH_SHORT).show()
    }


}