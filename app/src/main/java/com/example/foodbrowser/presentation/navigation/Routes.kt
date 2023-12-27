package com.example.foodbrowser.presentation.navigation

sealed class Routes(val route: String) {

    data object SearchFoodItems : Routes(route = Screen.SEARCH_FOOD_ITEMS)
    data object FoodItemDetails : Routes(route = Screen.FOOD_ITEM_DETAILS)

}