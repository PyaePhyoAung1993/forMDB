package com.example.themealdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.model.detailMeal.MealsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_all.view.*
import kotlinx.android.synthetic.main.search_item.view.*

class SearchMealAdapter(var search_item : List<MealsItem> =ArrayList()) :
  RecyclerView.Adapter<SearchMealViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMealViewHolder {
    return  SearchMealViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_item,parent,false))
  }

  override fun getItemCount(): Int {
    return search_item.size
  }

  override fun onBindViewHolder(holder: SearchMealViewHolder, position: Int) {

    holder.bindMeal(search_item[position])
  }

  fun updateSearchMeal(meal: List<MealsItem>)
  {
    this.search_item = meal
    notifyDataSetChanged()
  }

}


class SearchMealViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

  fun bindMeal(meal: MealsItem){
    itemView.search_meal_name.text = meal.strMeal

    Picasso.get()
      .load(meal.strMealThumb)
      .placeholder(R.drawable.ic_menu_camera)
      .into(itemView.search_meal_image)
  }
}