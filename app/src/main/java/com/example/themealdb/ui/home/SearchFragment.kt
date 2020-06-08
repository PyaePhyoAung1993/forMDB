package com.example.themealdb.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.adapter.MealAdapter
import com.example.themealdb.adapter.SearchMealAdapter
import com.example.themealdb.model.detailMeal.MealsItem
import com.example.themealdb.viewmodel.MealCategoryViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_meal.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {
  private lateinit var viewManager : RecyclerView.LayoutManager
  private var mealCategoryViewModel : MealCategoryViewModel = MealCategoryViewModel()
  private var mealAdapter : SearchMealAdapter = SearchMealAdapter()
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_search, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    recyler_search.layoutManager = viewManager
    recyler_search.adapter = mealAdapter
    searchObserView()
  }

  fun searchObserView(){
    mealCategoryViewModel.getsearchName().observe(viewLifecycleOwner, Observer {
        mealAdapter.updateSearchMeal(it.meals as List<MealsItem>)
    })
  }

  override fun onResume() {
    super.onResume()
    var messageArgs = arguments?.let { SearchFragmentArgs.fromBundle(it) }

    var searchMeal: String? = messageArgs?.searchByName
    Log.d("searchMeal",searchMeal.toString())
    mealCategoryViewModel.setSearchName(searchMeal.toString())
  }

}