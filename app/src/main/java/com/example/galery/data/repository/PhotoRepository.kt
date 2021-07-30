package com.example.galery.data.repository

import com.example.galery.data.network.PhotoService
import javax.inject.Inject

class PhotoRepository @Inject constructor(val photoService: PhotoService) {
    suspend fun getPhotos(): Any{
        return photoService.getPhotos()
    }
}