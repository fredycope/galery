package com.example.galery.data.repository

import androidx.lifecycle.LiveData
import com.example.masterdetail.dbroom.dbdao.GaleryDataDao
import com.example.masterdetail.dbroom.dbmodel.Galery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavRespository @Inject constructor(val galeryDataDao: GaleryDataDao) {

    suspend fun getFavorites(): List<Galery>{
        return withContext(Dispatchers.IO){
            galeryDataDao.getListFavorite()
        }
    }

    suspend fun saveFavorite(galery: Galery){
        withContext(Dispatchers.IO){
            galeryDataDao.insert(galery)
        }
    }

    suspend fun deletFavorite(galeryId: String){
        withContext(Dispatchers.IO){
            galeryDataDao.deleteId(galeryId)
        }
    }

}