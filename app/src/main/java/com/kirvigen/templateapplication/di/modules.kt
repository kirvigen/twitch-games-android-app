package com.kirvigen.templateapplication.di

import android.app.Application
import androidx.room.Room
import com.kirvigen.templateapplication.data.api.RetrofitClientManager
import com.kirvigen.templateapplication.data.database.TwichGameDb
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module{
    single { RetrofitClientManager().getApi() }
    single {
        Room
            .databaseBuilder(get(), TwichGameDb::class.java, "TwichGame.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single {  }
}