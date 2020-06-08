package com.example.themealdb.viewmodel

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themealdb.api.MealApi
import com.example.themealdb.model.detailMeal.SearchMeal
import com.example.themealdb.model.meal.Meal
import com.example.themealdb.model.mealCategory.MealCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealCategoryViewModel :  ViewModel(){
    var loading : MutableLiveData<Boolean> = MutableLiveData()
    var error : MutableLiveData<Boolean> = MutableLiveData()
    var result : MutableLiveData<MealCategory> = MutableLiveData()
    var meal : MutableLiveData<Meal> = MutableLiveData()
    var search_byName : MutableLiveData<SearchMeal> = MutableLiveData()
    private  var mealApi : MealApi = MealApi()

    fun getLoading() : LiveData<Boolean> = loading
    fun getError() : LiveData<Boolean> = error
    fun getResult() : LiveData<MealCategory> = result
    fun getMeal() : LiveData<Meal> = meal
    fun getsearchName() : LiveData<SearchMeal> = search_byName


  fun setResult(){
      loading.value =false
      var call = mealApi.getCategories()
      call.enqueue(object : Callback<MealCategory>{
        override fun onFailure(call: Call<MealCategory>, t: Throwable) {
          loading.value = false
          error.value = true
        }

        override fun onResponse(call: Call<MealCategory>, response: Response<MealCategory>) {
            loading.value = false
            var resultList = MealCategory(response?.body()?.categories?: emptyList())
            Log.d("meal_result","$resultList")
            result.value = resultList
        }

      })
  }

  fun setMeal(search: String)
  {
      val search_category = mealApi.getMeal(search)
      search_category.enqueue(object : Callback<Meal>{
        override fun onFailure(call: Call<Meal>, t: Throwable) {

        }

        override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
          var mealList = Meal(response.body()?.meals?: emptyList())
          Log.d("viewmodel","$mealList")
          meal.value = mealList
        }

      })
  }

  fun setSearchName(search_name : String){
    val search_meal = mealApi.serachMeal(search_name)
    search_meal.enqueue(object : Callback<SearchMeal>{
      override fun onFailure(call: Call<SearchMeal>, t: Throwable) {

      }

      override fun onResponse(call: Call<SearchMeal>, response: Response<SearchMeal>) {
        var searchList = SearchMeal(response?.body()?.meals?: emptyList())
        search_byName.value = searchList
      }

    })
  }

}