package com.example.foodbrowser.app.di

import com.example.foodbrowser.app.SchedulersProvider
import com.example.foodbrowser.data.service.ServicesInitializer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val appModule = module {

    single<ServicesInitializer> { ServicesInitializer() }

    //simplified
    single<SchedulersProvider> {
        object : SchedulersProvider {

            override val io: Scheduler get() = Schedulers.io()
            override val main: Scheduler get() = AndroidSchedulers.mainThread()
            override val computation: Scheduler get() = Schedulers.computation()

        }
    }

}