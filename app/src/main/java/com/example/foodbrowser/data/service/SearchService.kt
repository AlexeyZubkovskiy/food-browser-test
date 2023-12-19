package com.example.foodbrowser.data.service

import com.example.foodbrowser.data.entity.FoodItemDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


//https://uih0b7slze.execute-api.us-east-1.amazonaws.com/dev/search?kv=chicken

private const val QUERY_KEY = "kv"

interface SearchService {

    @GET("/dev/search")
    fun searchFoodItems(@Query(QUERY_KEY) query : String) : Single<List<FoodItemDTO>>

}