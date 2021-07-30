package com.example.galery.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoService @Inject constructor(val retrofitService: RetrofitService) {
    suspend fun getPhotos():Any{
        return withContext(Dispatchers.IO){
            retrofitService.getPhotos()
        }
    }
}