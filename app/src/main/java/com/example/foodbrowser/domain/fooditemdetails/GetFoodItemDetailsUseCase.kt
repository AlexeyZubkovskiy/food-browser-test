package com.example.foodbrowser.domain.fooditemdetails

import com.example.foodbrowser.domain.entity.ExtendedFoodItem
import io.reactivex.Single

interface GetFoodItemDetailsUseCase {

    fun getFoodItemDetails(id: String): Single<ExtendedFoodItem>

}