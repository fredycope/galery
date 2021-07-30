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
import com.example.galery.databinding.FragmentMainBinding
import com.example.galery.ui.viewmodel.FavViewModel
import com.example.galery.ui.viewmodel.MainViewModel
import com.example.masterdetail.dbroom.dbmodel.Galery
import com.google.gson.Gson
import com.google.gson.JsonArray
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(), OnClickList {
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null

 lateinit var binding: FragmentMainBinding
 lateinit var adapter: ImagesAdapter
 private val viewModel: MainViewModel by viewModels()
    private val viewModelFav: FavViewModel by viewModels()

 @Inject
 lateinit var nav: Nav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {

        }
        viewModel.onCreatePhotos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvImages.layoutManager = GridLayoutManager(requireContext(), 2).also {
            it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position % 3 == 0)
                        2
                    else
                        1
                }
            }
        }
        adapter = ImagesAdapter(this, true)
        binding.rvImages.adapter = adapter

        photos()
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

    fun photos(){
        viewModel.getListPhotos.observe(viewLifecycleOwner, Observer {
            val listData = ArrayList<ResponsePhotos>()
            for (responsePhotos in it as JsonArray) {
                val gson = Gson()
                listData.add(gson.fromJson(responsePhotos, ResponsePhotos::class.java))
            }

            initRecyclerView(listData)
        })
    }

    fun initRecyclerView(list: List<ResponsePhotos>){
        adapter.addData(list)
    }


    override fun goToFragment(result: Any, view: View) {
        val res = (result) as ResponsePhotos
        val bundle = bundleOf("response" to res)

        nav.gotoFragment(view,R.id.action_mainFragment_to_detailsFragment,bundle)
    }

    override fun clickFavorite(result: Any) {

        val gson = Gson()
        val newjson = gson.toJson(result)
        val galeryId = result as ResponsePhotos
        viewModelFav.saveFav(Galery(newjson, galeryId.id))

        Toast.makeText(requireContext(),"Agregado a favoritos",Toast.LENGTH_SHORT).show()
    }

}