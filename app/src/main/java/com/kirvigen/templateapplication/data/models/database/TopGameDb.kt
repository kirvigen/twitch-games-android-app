package com.kirvigen.templateapplication.data.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopGameDb(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name="image")
    val image:String,
    @ColumnInfo(name="channels")
    var channels:Int,
    @ColumnInfo(name="viewers")
    val viewers:Int,
    @ColumnInfo(name="name")
    val name:String
)
