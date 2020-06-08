package com.example.themealdb.api

import com.example.themealdb.model.detailMeal.SearchMeal
import com.example.themealdb.model.meal.Meal
import com.example.themealdb.model.mealCategory.MealCategory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealApi{
    var mealApiInterface : MealApiInterface

            companion object{
              const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
            }

            init {
              var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

              mealApiInterface = retrofit.create(MealApiInterface::class.java)
            }

    fun getCategories(): Call<MealCategory>{
      return  mealApiInterface.getCategory()
    }

    fun getMeal(c: String): Call<Meal>{
      return mealApiInterface.getMeal(c)
    }

  fun serachMeal(s: String): Call<SearchMeal>{
    return mealApiInterface.searchMeal(s)
  }




}