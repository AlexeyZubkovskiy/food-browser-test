package com.example.foodbrowser.domain.di

import com.example.foodbrowser.data.repo.FoodRepository
import com.example.foodbrowser.domain.fooditems.SearchSimpleFoodItemsUseCase
import com.example.foodbrowser.domain.fooditems.SearchSimpleFoodItemsUseCaseImpl
import org.koin.dsl.module

val searchFoodItemsDomainModule = module {
    factory<SearchSimpleFoodItemsUseCase> {  SearchSimpleFoodItemsUseCaseImpl(foodRepository =  get<FoodRepository>()) }
}