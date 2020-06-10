package com.example.themealdb.api

import android.util.Log
import com.example.themealdb.model.detailMeal.Meal
import com.example.themealdb.model.detailMeal.MealDetail

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

    fun getMeal(c: String): Call<MealDetail>{
      return mealApiInterface.getMeal(c)
    }

  fun serachMeal(s: String): Call<MealDetail>{
    return mealApiInterface.searchMeal(s)
  }

  fun serachById(i: String): Call<MealDetail>{

    return mealApiInterface.searchById(i)
  }




}