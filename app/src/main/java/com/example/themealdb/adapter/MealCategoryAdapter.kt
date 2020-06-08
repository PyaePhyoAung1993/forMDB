package com.example.themealdb.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.model.mealCategory.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_category.view.*

class MealCategoryAdapter(var itemList:List<Category> = ArrayList()):RecyclerView.Adapter<MealCategoryAdapter.MealCategoryViewHolder>() {
  var mClickListener :ClickLinstener? = null

  interface ClickLinstener{
    fun onClick(next: Category)
  }

  fun setOnClickListener(clickLinstener: ClickLinstener){
    this.mClickListener = clickLinstener
  }
  fun updateItem(category: List<Category>){
      this.itemList = category
      notifyDataSetChanged()
  }
  inner class MealCategoryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) , View.OnClickListener{
    override fun onClick(v: View) {
      mClickListener?.onClick(category)
    }
    private lateinit var category :Category
    private var view : View = itemView
    init {
      view.setOnClickListener(this)
    }
    fun bindData(category:Category){
      this.category = category
       view.meal_category.text = category.strCategory
      view.meal_description.text = category.strCategoryDescription
      Picasso.get()
        .load(category.strCategoryThumb)
        .placeholder(R.drawable.ic_menu_gallery)
        .into(view.meal_image)
    }


  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealCategoryViewHolder {
    var view = LayoutInflater.from(parent.context).inflate(R.layout.meal_category,parent,false)
    return MealCategoryViewHolder(view)
  }

  override fun getItemCount(): Int {
    Log.d("meal","item ${itemList.size}")
   return itemList.size
  }
  override fun onBindViewHolder(holder: MealCategoryViewHolder, position: Int) {
    holder.bindData(itemList[position])
  }
}