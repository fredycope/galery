package com.example.galery.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galery.domain.FavUseCase
import com.example.masterdetail.dbroom.dbmodel.Galery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(val favUseCase: FavUseCase): ViewModel(){
    val getFav = MutableLiveData<List<Galery>>()

    fun getFav(){
        viewModelScope.launch {
            getFav.postValue(favUseCase.getFavorites())
        }
    }

    fun saveFav(galery: Galery){
        viewModelScope.launch {
            favUseCase.saveFavorite(galery)
        }
    }

    fun deleteFav(galeryId: String){
        viewModelScope.launch {
            favUseCase.deleteFav(galeryId)
            getFav.postValue(favUseCase.getFavorites())
        }
    }
}