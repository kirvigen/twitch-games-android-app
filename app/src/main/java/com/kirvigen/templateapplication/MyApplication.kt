package com.kirvigen.templateapplication

import android.app.Application
import com.kirvigen.templateapplication.di.mainModule
import com.kirvigen.templateapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(mainModule, viewModelModule)
        }
    }
}