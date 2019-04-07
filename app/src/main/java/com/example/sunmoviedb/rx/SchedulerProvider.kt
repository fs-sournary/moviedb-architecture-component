package com.example.sunmoviedb.rx

import io.reactivex.Scheduler

/**
 * Created on 4/5/19 by Sang
 * Description:
 **/
interface SchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler
}
