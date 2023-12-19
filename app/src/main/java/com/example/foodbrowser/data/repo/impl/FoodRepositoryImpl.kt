package com.example.foodbrowser.data.repo.impl

import com.example.foodbrowser.app.SchedulersProvider
import com.example.foodbrowser.data.entity.FoodItemDTO
import com.example.foodbrowser.data.repo.FoodRepository
import com.example.foodbrowser.data.service.SearchService
import io.reactivex.Single
import java.util.Collections

// in single module internal keyword unnecessary, but in multi-module it will hide implementation
internal class FoodRepositoryImpl(
    private val schedulersProvider: SchedulersProvider,
    private val searchService: SearchService
) : FoodRepository {

    private val simpleLastCache: MutableList<FoodItemDTO> =
        Collections.synchronizedList(mutableListOf<FoodItemDTO>())

    override fun searchFoodItems(query: String): Single<List<FoodItemDTO>> = searchService
        .searchFoodItems(query)
        .doOnSuccess { new ->
            simpleLastCache.clear()
            simpleLastCache.addAll(new)
        }
        .subscribeOn(schedulersProvider.io)

    override fun getFoodItem(id: String): Single<FoodItemDTO> = Single.just(
        simpleLastCache.firstOrNull { item -> item.id == id } ?: emptyFoodItemDTO
    )

}

private val emptyFoodItemDTO: FoodItemDTO
    get() = FoodItemDTO(
        id = "",
        name = "",
        brand = "",
        calories = 0,
        portionInGrams = 0
    )