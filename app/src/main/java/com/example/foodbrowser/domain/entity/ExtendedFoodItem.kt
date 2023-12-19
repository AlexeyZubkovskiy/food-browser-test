package com.example.foodbrowser.domain.entity

data class ExtendedFoodItem(
    val id :String,
    val name: String,
    val brand:String,
    val calories: Int,
    val portionInGrams: Int
)
