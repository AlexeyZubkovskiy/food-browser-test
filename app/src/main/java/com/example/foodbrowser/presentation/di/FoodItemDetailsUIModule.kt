package com.example.foodbrowser.presentation.di

import  com.example.foodbrowser.presentation.screen.detail.FoodItemDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val foodItemDetailsUIModule = module {
    viewModelOf(::FoodItemDetailsViewModel)
}