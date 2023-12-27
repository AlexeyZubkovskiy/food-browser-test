package com.example.foodbrowser.app

import io.reactivex.Scheduler

interface SchedulersProvider {

    val io: Scheduler

    val main: Scheduler

    val computation: Scheduler

}