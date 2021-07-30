package com.example.galery.domain

import androidx.lifecycle.LiveData
import com.example.galery.data.repository.FavRespository
import com.example.masterdetail.dbroom.dbmodel.Galery
import javax.inject.Inject

class FavUseCase @Inject constructor(val favRespository: FavRespository) {
    suspend fun getFavorites(): List<Galery> {
        return favRespository.getFavorites()
    }

    suspend fun saveFavorite(galery: Galery){
         favRespository.saveFavorite(galery)
    }

    suspend fun deleteFav(galeryId: String){
        favRespository.deletFavorite(galeryId)
    }
}