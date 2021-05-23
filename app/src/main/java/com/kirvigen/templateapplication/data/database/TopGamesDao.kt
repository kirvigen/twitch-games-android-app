package com.kirvigen.templateapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kirvigen.templateapplication.data.models.database.TopGameDb
import kotlinx.coroutines.Deferred


@Dao
interface TopGamesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(listTopGames: List<TopGameDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(topGameDb: TopGameDb)

    @Query("SELECT * FROM `topgamedb` ORDER BY viewers DESC")
    suspend fun getTopGames():List<TopGameDb>
}