package com.example.foodbrowser.data.di

import com.example.foodbrowser.app.SchedulersProvider
import com.example.foodbrowser.data.repo.FoodRepository
import com.example.foodbrowser.data.repo.impl.FoodRepositoryImpl
import com.example.foodbrowser.data.service.SearchService
import com.example.foodbrowser.data.service.ServicesInitializer
import org.koin.dsl.module

val foodModule = module {

    factory<SearchService> { get<ServicesInitializer>().init(SearchService::class.java) }

    single<FoodRepository> {
        FoodRepositoryImpl(
            schedulersProvider = get<SchedulersProvider>(),
            searchService = get<SearchService>()
        )
    }

}