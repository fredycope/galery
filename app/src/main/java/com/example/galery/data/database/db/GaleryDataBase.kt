package com.example.masterdetail.dbroom.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.masterdetail.dbroom.dbdao.GaleryDataDao
import com.example.masterdetail.dbroom.dbmodel.Galery

@Database(entities = [Galery::class], version = 1, exportSchema = false)
abstract class GaleryDataBase : RoomDatabase(){
    abstract fun galeryDataDao(): GaleryDataDao
}