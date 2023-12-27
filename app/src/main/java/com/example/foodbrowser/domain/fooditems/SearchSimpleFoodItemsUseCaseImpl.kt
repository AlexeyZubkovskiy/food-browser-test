package com.example.foodbrowser.domain.fooditems

import com.example.foodbrowser.data.entity.FoodItemDTO
import com.example.foodbrowser.data.repo.FoodRepository
import com.example.foodbrowser.domain.common.Sorted
import com.example.foodbrowser.domain.entity.SimpleFoodItem
import io.reactivex.Single

internal class SearchSimpleFoodItemsUseCaseImpl(
    private val foodRepository: FoodRepository
) : SearchSimpleFoodItemsUseCase {

    override fun search(
        query: String,
        sortedBy: SearchSimpleFoodItemsUseCase.SortedBy,
        order: Sorted,
        uniqueOnly: Boolean
    ): Single<List<SimpleFoodItem>> =
        //for complex optimisations Sequence can be used here, but I just simplified it
        foodRepository.searchFoodItems(query).map { items ->
            val filteredIfNeeded = if (uniqueOnly) items.distinctBy { it.id } else items
            filteredIfNeeded
                .map { it.toSimpleFoodItem() }
                .let { list ->
                    when (order) {
                        Sorted.ASCEND -> list.sortedBy { item -> item.sortField(sortedBy) }
                        Sorted.DESCEND -> list.sortedByDescending { item -> item.sortField(sortedBy) }
                        Sorted.NONE -> list
                    }
                }
        }

}

private fun FoodItemDTO.toSimpleFoodItem(): SimpleFoodItem = SimpleFoodItem(id = this.id, name = this.name)

private fun SimpleFoodItem.sortField(sortedBy: SearchSimpleFoodItemsUseCase.SortedBy): String =
    when (sortedBy) {
        SearchSimpleFoodItemsUseCase.SortedBy.NAME -> this.name
    }