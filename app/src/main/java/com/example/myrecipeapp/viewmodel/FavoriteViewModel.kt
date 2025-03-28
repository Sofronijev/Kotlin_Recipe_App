package com.example.myrecipeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipeapp.local.dao.FavoriteMealDao
import com.example.myrecipeapp.local.entities.FavoriteMeal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteMealDao: FavoriteMealDao) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    private val _favoriteMeals = MutableStateFlow<List<FavoriteMeal>>(emptyList())

    val favoriteMeals: StateFlow<List<FavoriteMeal>> = _favoriteMeals
    val isLoading: StateFlow<Boolean> = _isLoading


    init {
        fetchFavoriteMeals()
    }

    private fun fetchFavoriteMeals() {
        viewModelScope.launch {
            _isLoading.value = true
            favoriteMealDao.fetchFavoriteMeals()
                .collect { meals ->
                    _favoriteMeals.value = meals
                    _isLoading.value = false
                }
        }
    }

    fun isMealFavorite(mealId: String): Flow<Boolean> {
        return favoriteMealDao.isMealFavorite(mealId)
    }

    fun addMealToFavorites(idMeal: Int, strMeal: String, strMealThumb: String) {
        val meal = FavoriteMeal(idMeal = idMeal, strMeal = strMeal, strMealThumb = strMealThumb)
        viewModelScope.launch {
            _isLoading.value = true
            favoriteMealDao.insertFavoriteMeal(meal)
            _isLoading.value = false
        }
    }

    fun deleteMealFromFavorites(mealId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            favoriteMealDao.removeFavoriteMealById(mealId)
            _isLoading.value = false
        }
    }

}