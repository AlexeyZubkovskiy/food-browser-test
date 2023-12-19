package com.example.foodbrowser.app

import android.app.Application
import com.example.foodbrowser.app.di.appModule
import com.example.foodbrowser.data.di.foodModule
import com.example.foodbrowser.domain.di.foodItemDetailsDomainModule
import com.example.foodbrowser.domain.di.searchFoodItemsDomainModule
import com.example.foodbrowser.presentation.di.foodItemDetailsUIModule
import com.example.foodbrowser.presentation.di.searchFoodItemsUIModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FoodBrowserApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FoodBrowserApp)
            modules(
                listOf(
                    appModule,
                    foodModule,
                    searchFoodItemsDomainModule,
                    searchFoodItemsUIModule,
                    foodItemDetailsDomainModule,
                    foodItemDetailsUIModule
                )
            )
        }

    }
}
