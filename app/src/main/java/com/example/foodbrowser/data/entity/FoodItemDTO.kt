package com.example.foodbrowser.data.entity

import com.google.gson.annotations.SerializedName

data class FoodItemDTO(
    @SerializedName("id")
    val id :String,
    @SerializedName("name")
    val name: String,
    @SerializedName("brand")
    val brand:String,
    @SerializedName("calories")
    val calories: Int,
    @SerializedName("portion")
    val portionInGrams: Int
)
