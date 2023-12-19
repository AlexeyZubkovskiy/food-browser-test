package com.example.foodbrowser.domain.fooditems

import com.example.foodbrowser.domain.common.Sorted
import com.example.foodbrowser.domain.entity.SimpleFoodItem
import io.reactivex.Single

interface SearchSimpleFoodItemsUseCase {

    enum class SortedBy {
        NAME
    }

    fun search(
        query: String,
        sortedBy: SortedBy = SortedBy.NAME,
        order: Sorted = Sorted.NONE,
        uniqueOnly: Boolean = false
    ): Single<List<SimpleFoodItem>>

}