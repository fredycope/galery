package com.example.galery.di

import com.example.galery.data.network.RetrofitService
import com.example.galery.data.utils.Constants.BASE_URL
import com.example.galery.data.utils.Constants.TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun providesRetrofit(): Retrofit {
        val httpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", TOKEN)
                .header("Connection", "close")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
}


