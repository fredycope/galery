package com.example.masterdetail.dbroom.dbdao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.masterdetail.dbroom.dbmodel.Galery

@Dao
interface GaleryDataDao {
    @Insert
    suspend fun insert(galery: Galery)

    @Update
    suspend fun update(vararg galery: Galery)

    @Delete
    suspend fun delete(vararg galery: Galery)

    @Query("DELETE FROM "+Galery.TABLE_NAME+" WHERE galery_id = :galeryId")
    suspend fun deleteId(galeryId: String)

    @Query("SELECT * FROM " + Galery.TABLE_NAME)
    suspend fun getListFavorite(): List<Galery>
}