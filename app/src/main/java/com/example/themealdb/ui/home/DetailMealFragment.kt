package com.example.themealdb.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.adapter.MealDetailAdapter
import com.example.themealdb.adapter.SearchMealAdapter
import com.example.themealdb.viewmodel.MealCategoryViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_meal.*
import kotlinx.android.synthetic.main.meal_all.view.*

class DetailMealFragment : Fragment() {
  private lateinit var viewManager : RecyclerView.LayoutManager
  private var mealCategoryViewModel : MealCategoryViewModel = MealCategoryViewModel()
  private var mealDetailAdapter : MealDetailAdapter = MealDetailAdapter()
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_detail_meal, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
   mealCategoryViewModel = ViewModelProviders.of(this).get(MealCategoryViewModel::class.java)
    viewManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    recyler_detail.layoutManager = viewManager
    recyler_detail.adapter = mealDetailAdapter
    mealCategoryViewModel.getsearchId().observe(viewLifecycleOwner, Observer {
       mealDetailAdapter.updateSearchMeal(it.meals)

    })
//    mealCategoryViewModel.getsearchId().observe(viewLifecycleOwner, Observer {
//      deail_name.text = it.meals
      //      Picasso.get()
//        .load(it.strMealThumb)
//        .placeholder(R.drawable.ic_menu_camera)
//        .into(detail_image)
//
//      detail_category.text = it.strCategory
//      txt_instruction.text = it.strInstructions
//    })
  }




  override fun onResume() {
    super.onResume()
    var messageArg = arguments?.let {DetailMealFragmentArgs.fromBundle(it) }
    var meal_id = messageArg?.searchById
    (activity as AppCompatActivity).supportActionBar?.title = meal_id
    mealCategoryViewModel.setSearchById(meal_id.toString())

  }





}