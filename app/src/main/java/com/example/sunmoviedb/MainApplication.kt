package com.example.sunmoviedb

import android.app.Application
import com.example.sunmoviedb.di.appModule
import com.example.sunmoviedb.di.networkModule
import com.example.sunmoviedb.di.repositoryModule
import com.example.sunmoviedb.di.viewModelModule
import org.koin.core.context.startKoin

/**
 * Created on 4/3/19 by Sang
 * Description:
 **/
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { modules(appModule, networkModule, viewModelModule, repositoryModule) }
    }
}
