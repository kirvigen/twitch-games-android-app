package com.kirvigen.templateapplication.di

import androidx.room.Room
import com.kirvigen.templateapplication.data.api.RetrofitClientManager
import com.kirvigen.templateapplication.data.database.TwitchGameDb
import org.koin.dsl.module

val mainModule = module{
    single { RetrofitClientManager().getApi() }
    single {
        Room
            .databaseBuilder(get(), TwitchGameDb::class.java, "TwichGame.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single {  }
}