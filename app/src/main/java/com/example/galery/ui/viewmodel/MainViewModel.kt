package com.example.galery.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galery.data.models.ResponsePhotos
import com.example.galery.domain.PhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val case: PhotoUseCase):ViewModel() {
    val getListPhotos = MutableLiveData<Any>()
    fun onCreatePhotos(){
        viewModelScope.launch{
            val res = case.getPhotos()
            getListPhotos.postValue(res)
        }
    }
}