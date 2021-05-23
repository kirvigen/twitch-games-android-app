package com.kirvigen.templateapplication.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kirvigen.templateapplication.data.api.RetrofitClientManager
import com.kirvigen.templateapplication.data.api.repositories.TwitchRepository
import com.kirvigen.templateapplication.data.database.TopGamesDao
import com.kirvigen.templateapplication.data.database.TwitchGameDb
import com.kirvigen.templateapplication.ui.fragments.listGames.ListGameViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { RetrofitClientManager().getApi() }
    single {
        Room.databaseBuilder(androidApplication(), TwitchGameDb::class.java, "TwitchGame.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<TwitchGameDb>().TopGamesDao() }
    single { TwitchRepository(get(), get()) }
}
val viewModelModule = module {
    viewModel { ListGameViewModel(get()) }
}