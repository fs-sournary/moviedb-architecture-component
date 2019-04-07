package com.example.sunmoviedb.di

import com.example.sunmoviedb.rx.AndroidSchedulerProvider
import com.example.sunmoviedb.rx.SchedulerProvider
import org.koin.dsl.module

/**
 * Created on 4/5/19 by Sang
 * Description:
 **/
val appModule = module {
    single<SchedulerProvider> { AndroidSchedulerProvider() }
}
