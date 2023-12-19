package com.example.foodbrowser.domain.fooditemdetails

import com.example.foodbrowser.data.entity.FoodItemDTO
import com.example.foodbrowser.data.repo.FoodRepository
import com.example.foodbrowser.domain.entity.ExtendedFoodItem
import io.reactivex.Single

internal class GetFoodItemDetailsUseCaseImpl(private val foodRepository: FoodRepository) : GetFoodItemDetailsUseCase {
    override fun getFoodItemDetails(id: String): Single<ExtendedFoodItem> =
        foodRepository.getFoodItem(id)
            .map { it.toExtendedFoodItem() }

}

private fun FoodItemDTO.toExtendedFoodItem() = ExtendedFoodItem(
    id= this.id,
    name = this.name,
    brand  = this.brand,
    calories = this.calories,
    portionInGrams = this.portionInGrams
)