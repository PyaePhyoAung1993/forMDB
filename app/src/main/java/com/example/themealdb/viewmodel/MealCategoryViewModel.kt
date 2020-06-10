package com.example.themealdb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themealdb.api.MealApi
import com.example.themealdb.model.detailMeal.Meal
import com.example.themealdb.model.detailMeal.MealDetail
import com.example.themealdb.model.mealCategory.MealCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealCategoryViewModel :  ViewModel(){
    var loading : MutableLiveData<Boolean> = MutableLiveData()
    var error : MutableLiveData<Boolean> = MutableLiveData()
    var result : MutableLiveData<MealCategory> = MutableLiveData()
    var meal : MutableLiveData<MealDetail> = MutableLiveData()
    var search_byName : MutableLiveData<MealDetail> = MutableLiveData()
    var search_byId : MutableLiveData<MealDetail> = MutableLiveData()
    private  var mealApi : MealApi = MealApi()

    fun getLoading() : LiveData<Boolean> = loading
    fun getError() : LiveData<Boolean> = error
    fun getResult() : LiveData<MealCategory> = result
    fun getMeal() : LiveData<MealDetail> = meal
    fun getsearchName() : LiveData<MealDetail> = search_byName
    fun getsearchId() : LiveData<MealDetail> = search_byId


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
      search_category.enqueue(object : Callback<MealDetail>{
        override fun onFailure(call: Call<MealDetail>, t: Throwable) {

        }

        override fun onResponse(call: Call<MealDetail>, response: Response<MealDetail>) {
          var mealList = MealDetail(response.body()?.meals?: emptyList())
          Log.d("viewmodel","$mealList")
          meal.value = mealList
        }

      })
  }

  fun setSearchName(search_name : String){
    val search_meal = mealApi.serachMeal(search_name)
    search_meal.enqueue(object : Callback<MealDetail>{
      override fun onFailure(call: Call<MealDetail>, t: Throwable) {

      }

      override fun onResponse(call: Call<MealDetail>, response: Response<MealDetail>) {
        var searchList = MealDetail(response?.body()?.meals?: emptyList())
        search_byName.value = searchList
      }

    })
  }

//  fun setSearcById(search_id :  String){
//    val searchId = mealApi.serachById(search_id)
//    searchId.enqueue(object : Callback<SearchMeal>{
//      override fun onFailure(call: Call<SearchMeal>, t: Throwable) {
//        TODO("Not yet implemented")
//      }
//
//      override fun onResponse(call: Call<SearchMeal>, response: Response<SearchMeal>) {
//        var idlist = SearchMeal(response.body()?.meals?: emptyList())
//        search_byId.value = idlist
//      }
//
//    })
//  }

  fun setSearchById(search_id : String){
    val searchId = mealApi.serachById(search_id)
    searchId.enqueue(object : Callback<MealDetail>{
      override fun onFailure(call: Call<MealDetail>, t: Throwable) {
        TODO("Not yet implemented")
      }

      override fun onResponse(call: Call<MealDetail>, response: Response<MealDetail>) {
        var mealList  = MealDetail(response.body()?.meals?: emptyList())
        Log.d("mealList" ,"$mealList")
        search_byId.value = mealList
      }


    })
  }

}