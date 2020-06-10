package com.example.themealdb.api

import com.example.themealdb.model.detailMeal.Meal
import com.example.themealdb.model.detailMeal.MealDetail

import com.example.themealdb.model.mealCategory.MealCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiInterface{

@GET("categories.php")
fun getCategory(): Call<MealCategory>


@GET("filter.php")
fun getMeal(
  @Query("c")
  c:String): Call<MealDetail>

  @GET("search.php")
  fun searchMeal(
    @Query("s")
    s:String): Call<MealDetail>

  @GET("lookup.php")
  fun searchById(
    @Query("i")
    i:String): Call<MealDetail>

}