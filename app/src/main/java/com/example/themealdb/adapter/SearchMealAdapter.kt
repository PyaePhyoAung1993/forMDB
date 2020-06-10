package com.example.themealdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.model.detailMeal.Meal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_item.view.*

class SearchMealAdapter(var search_ : List<Meal> =ArrayList()) :
  RecyclerView.Adapter<SearchMealViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMealViewHolder {
    return  SearchMealViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_item,parent,false))
  }

  override fun getItemCount(): Int {
    return search_.size
  }

  override fun onBindViewHolder(holder: SearchMealViewHolder, position: Int) {

    holder.bindMeal(search_[position])
  }

  fun updateSearchMeal(mealsItem: List<Meal>)
  {
    this.search_ = mealsItem
    notifyDataSetChanged()
  }

}


class SearchMealViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

  fun bindMeal(mealsItem: Meal){
    itemView.search_meal_name.text = mealsItem.strMeal

    Picasso.get()
      .load(mealsItem.strMealThumb)
      .placeholder(R.drawable.ic_menu_camera)
      .into(itemView.search_meal_image)
  }
}