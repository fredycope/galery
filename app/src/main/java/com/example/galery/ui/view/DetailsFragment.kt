package com.example.galery.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.galery.R
import com.example.galery.data.models.ResponsePhotos
import com.example.galery.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
lateinit var binding: FragmentDetailsBinding
    lateinit var response: ResponsePhotos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            response = arguments?.get("response") as ResponsePhotos
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInit()
    }

    fun setInit(){
        Glide.with(binding.ivProfile).load(response.urls.full).into(binding.ivProfile)

        binding.tvProfileTitle.text = response.user.name
        binding.tvProfileBio.text = response.user.bio
        binding.tvProfileLocation.text = response.user.location ?: "Default"
        binding.tvProfileLikes.text = "likes ".plus(response.likes.toString())
        binding.tvProfilePhotos.text = response.user.instagram_username
        binding.tvProfileCollections.text = response.user.twitter_username
    }
}