package com.example.galery.di

import android.content.Context
import androidx.room.Room
import com.example.masterdetail.dbroom.db.GaleryDataBase
import com.example.masterdetail.dbroom.dbdao.GaleryDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): GaleryDataBase {
        return Room.databaseBuilder(
            appContext,
            GaleryDataBase::class.java,
            "Galery"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideGaleryDataDao(galeryDataBase: GaleryDataBase): GaleryDataDao {
        return galeryDataBase.galeryDataDao()
    }

}