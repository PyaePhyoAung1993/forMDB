package com.example.themealdb.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.model.detailMeal.Meal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_detail_item.view.*
import kotlinx.android.synthetic.main.search_item.view.*

class MealDetailAdapter(var search_ : List<Meal> =ArrayList()) :
  RecyclerView.Adapter<MealDetailViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealDetailViewHolder {
    return  MealDetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.meal_detail_item,parent,false))
  }

  override fun getItemCount(): Int {
    return search_.size
  }

  override fun onBindViewHolder(holder: MealDetailViewHolder, position: Int) {

    holder.bindMeal(search_[position])
  }

  fun updateSearchMeal(mealsItem: List<Meal>)
  {

    this.search_ = mealsItem
    notifyDataSetChanged()
  }

}


class MealDetailViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

  fun bindMeal(mealsItem: Meal){
    itemView.deail_name.text = mealsItem.strMeal

    Picasso.get()
      .load(mealsItem.strMealThumb)
      .placeholder(R.drawable.ic_menu_camera)
      .into(itemView.detail_image)
    itemView.detail_category.text = mealsItem.strCategory
    itemView.txt_instruction.text = mealsItem.strInstructions
    itemView.ingredient1.text = mealsItem.strIngredient1
    itemView.ingredient2.text =mealsItem.strIngredient12

  }
}