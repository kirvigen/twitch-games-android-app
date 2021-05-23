package com.kirvigen.templateapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kirvigen.templateapplication.data.models.database.TopGameDb

@Database(
    entities = [TopGameDb::class],
    version = 1,
    exportSchema = false
)

abstract class TwichGameDb : RoomDatabase() {
    abstract fun TopGamesDao(): TopGameDb
}
