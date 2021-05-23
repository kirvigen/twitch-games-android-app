package com.kirvigen.templateapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kirvigen.templateapplication.data.models.database.TopGameDb


@Dao
interface TopGamesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(listTopGames: List<TopGameDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(topGameDb: TopGameDb)

    @Query("SELECT * FROM `topgamedb` ORDER BY viewers DESC")
    fun getTopGames():List<TopGameDb>
}