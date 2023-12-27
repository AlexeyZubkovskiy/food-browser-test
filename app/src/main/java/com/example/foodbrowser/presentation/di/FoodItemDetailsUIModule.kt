package com.example.foodbrowser.presentation.di

import  com.example.foodbrowser.presentation.screen.detail.FoodItemDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val foodItemDetailsUIModule = module {

    viewModel<FoodItemDetailsViewModel> { (itemId: String) ->
        FoodItemDetailsViewModel(
            itemId = itemId,
            schedulersProvider = get(),
            foodItemDetailsUseCase = get()
        )
    }

}