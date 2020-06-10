package com.example.themealdb.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.adapter.MealAdapter
import com.example.themealdb.model.detailMeal.Meal

import com.example.themealdb.viewmodel.MealCategoryViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_meal.*
import kotlinx.android.synthetic.main.nav_header_main.view.*


class MealFragment : Fragment() , MealAdapter.ClickLinstener{
    private lateinit var viewManager : RecyclerView.LayoutManager
    private var mealCategoryViewModel : MealCategoryViewModel = MealCategoryViewModel()
    private var mealAdapter : MealAdapter = MealAdapter()
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_meal, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    meal.layoutManager = viewManager
    meal.adapter = mealAdapter
    mealAdapter.setOnClickListener(this)
    obserViewModel()

  }

   fun obserViewModel(){
    mealCategoryViewModel = ViewModelProviders.of(this).get(MealCategoryViewModel::class.java)
    mealCategoryViewModel.getMeal().observe(viewLifecycleOwner, Observer {
        mealAdapter.updateMeal(it.meals)

      Log.d("mealValue","${it.meals}")
    })
  }

  override fun onResume() {
    super.onResume()
    var messageArgs = arguments?.let { MealFragmentArgs.fromBundle(it) }

    var searchMeal: String? = messageArgs?.search
    Log.d("searchMeal",searchMeal.toString())
    (activity as AppCompatActivity).supportActionBar?.title = searchMeal
    mealCategoryViewModel.setMeal(searchMeal.toString())
  }


  override fun onClick(next: Meal) {
    var action = MealFragmentDirections.actionMealFragmentToDetailMealFragment(next.idMeal)
    findNavController().navigate(action)
  }
}