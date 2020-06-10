package com.example.themealdb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.adapter.MealCategoryAdapter
import com.example.themealdb.model.mealCategory.Category
import com.example.themealdb.viewmodel.MealCategoryViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), MealCategoryAdapter.ClickLinstener {

    private  var mealCategoryViewModel : MealCategoryViewModel = MealCategoryViewModel()
    private var mealCategoryadapter:MealCategoryAdapter = MealCategoryAdapter()
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)


        return root
    }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    recyler_meal.layoutManager = viewManager
    recyler_meal.adapter = mealCategoryadapter
    mealCategoryadapter.setOnClickListener(this)
    observeViewModel()

    search_meal.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
      if (actionId == EditorInfo.IME_ACTION_DONE) {
        val text = v.text.toString()
        var action = HomeFragmentDirections.actionNavHomeToSearchFragment(text)
        findNavController().navigate(action)
        return@OnEditorActionListener true
      }
      false
    })

  }

  fun observeViewModel(){
    mealCategoryViewModel = ViewModelProviders.of(this).get(MealCategoryViewModel::class.java)
    mealCategoryViewModel.getResult().observe(viewLifecycleOwner, Observer {
      mealCategoryadapter.updateItem(it.categories)

    })

    mealCategoryViewModel.setResult()
  }

  override fun onClick(next: Category) {
    Log.d("searchName","${next.strCategory}")
    mealCategoryViewModel.setMeal(next.strCategory)
    var action = HomeFragmentDirections.actionNavHomeToMealFragment(next.strCategory)
    findNavController().navigate(action)
  }
}