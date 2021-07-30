package com.example.galery.domain

import com.example.galery.data.repository.PhotoRepository
import javax.inject.Inject


class PhotoUseCase @Inject constructor(val repository: PhotoRepository) {
    suspend fun getPhotos(): Any{
        return repository.getPhotos()
    }
}