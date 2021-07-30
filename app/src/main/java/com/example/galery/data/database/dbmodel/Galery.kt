package com.example.masterdetail.dbroom.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Galery.TABLE_NAME)
class Galery (
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "galery_id") val galeryId: String,

    ) {
        companion object{
            const val TABLE_NAME = "favorite"
        }
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "fav_id")
        var noteId = 0
}