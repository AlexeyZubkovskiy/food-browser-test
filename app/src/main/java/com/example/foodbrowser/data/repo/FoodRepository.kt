package com.example.foodbrowser.data.repo

import com.example.foodbrowser.data.entity.FoodItemDTO
import io.reactivex.Single

interface FoodRepository {
    fun searchFoodItems(query: String): Single<List<FoodItemDTO>>

    fun getFoodItem(id: String): Single<FoodItemDTO>

}