package com.example.foodbrowser.domain.di

import com.example.foodbrowser.data.repo.FoodRepository
import com.example.foodbrowser.domain.fooditemdetails.GetFoodItemDetailsUseCase
import com.example.foodbrowser.domain.fooditemdetails.GetFoodItemDetailsUseCaseImpl
import org.koin.dsl.module

val foodItemDetailsDomainModule = module {

    factory<GetFoodItemDetailsUseCase> {
        GetFoodItemDetailsUseCaseImpl(foodRepository = get<FoodRepository>())
    }

}