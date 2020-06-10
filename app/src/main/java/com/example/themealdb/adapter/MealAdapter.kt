package com.example.themealdb.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.model.detailMeal.Meal

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_all.view.*

class MealAdapter(var mealList:List<Meal> = ArrayList()) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
  var mClickListener : ClickLinstener? = null
  interface ClickLinstener{
    fun onClick(next: Meal)
  }
  fun setOnClickListener(clickLinstener: ClickLinstener){
    this.mClickListener = clickLinstener
  }

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

  fun updateMeal(meal: List<Meal>)
  {
    this.mealList = meal
    notifyDataSetChanged()
  }

 inner class MealViewHolder(itemView:View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
    private lateinit var mealx :Meal
    private var view : View = itemView
    init {
      view.setOnClickListener(this)
         }
    fun bindMeal(meal:Meal){
      this.mealx = meal
      view.meal_name.text = mealx.strMeal

      Picasso.get()
        .load(mealx.strMealThumb)
        .placeholder(R.drawable.ic_menu_camera)
        .into(view.c_meal_image)
    }

    override fun onClick(v: View?) {
      mClickListener?.onClick(mealx)
    }


  }

}

