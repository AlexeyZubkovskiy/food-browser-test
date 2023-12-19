package com.example.foodbrowser.presentation.di

import com.example.foodbrowser.presentation.screen.search.SearchFoodItemsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val searchFoodItemsUIModule = module {
    viewModelOf(::SearchFoodItemsViewModel)
}
