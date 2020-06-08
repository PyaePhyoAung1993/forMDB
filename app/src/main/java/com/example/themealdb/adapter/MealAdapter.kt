package com.example.themealdb.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.model.meal.MealX
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_all.view.*

class MealAdapter(var mealList:List<MealX> = ArrayList()) : RecyclerView.Adapter<MealViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
    return  MealViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.meal_all,parent,false))
  }

  override fun getItemCount(): Int {
    Log.d("meal_data","Meal +> $mealList")
    return mealList.size
  }

  override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
     holder.bindMeal(mealList[position])
  }

  fun updateMeal(meal: List<MealX>)
  {
    this.mealList = meal
    notifyDataSetChanged()
  }

}

class MealViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

  fun bindMeal(meal:MealX){
    itemView.meal_name.text = meal.strMeal

    Picasso.get()
      .load(meal.strMealThumb)
      .placeholder(R.drawable.ic_menu_camera)
      .into(itemView.c_meal_image)
  }
}