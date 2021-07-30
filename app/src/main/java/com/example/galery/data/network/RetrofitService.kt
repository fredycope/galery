package com.example.galery.data.network

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("photos")
    suspend fun getPhotos(): JsonArray

    @GET("movie/popular")
    suspend fun getListPopular(@Query("api_key") api_key: String,
                               @Query("language") language: String,
                               @Query("page") page: String): JsonObject

    @GET("movie/{id_movie}")
    suspend fun getMovie(@Path("id_movie") id_movie: String,
                         @Query("api_key") api_key: String,
                         @Query("language") language: String):JsonObject
}