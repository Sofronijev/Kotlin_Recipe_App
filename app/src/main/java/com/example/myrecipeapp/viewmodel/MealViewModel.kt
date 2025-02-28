package com.example.myrecipeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myrecipeapp.model.MealDetail
import com.example.myrecipeapp.network.KtorClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealViewModel(private val ktorClient: KtorClient, mealId: Int) : ViewModel() {
    private val _meal = MutableStateFlow<MealDetail?>(null)
    private val _isLoading = MutableStateFlow<Boolean>(false)
    private val _error = MutableStateFlow<String>("")

    val meal: StateFlow<MealDetail?> = _meal
    val isLoading: StateFlow<Boolean> = _isLoading
    val error: StateFlow<String> = _error

    init {
        fetchMealDetails(mealId)
    }

    private fun fetchMealDetails(mealId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _meal.value = ktorClient.getMealDetails(mealId)
            } catch (e: Exception) {
                _error.value = "Error fetching meal details - ${e.message ?: "NOPE"}"
            } finally {
                _isLoading.value = false
            }
        }
    }


    companion object {
        // Define a custom key for your dependency
        val KTOR_CLIENT_KEY = object : CreationExtras.Key<KtorClient> {}
        val MEAL_ID_KEY = object : CreationExtras.Key<Int> {}

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Get the dependency in your factory
                val ktorClient = this[KTOR_CLIENT_KEY] as KtorClient
                val mealId = this[MEAL_ID_KEY] as Int
                MealViewModel(
                    ktorClient = ktorClient,
                    mealId = mealId,
                )
            }
        }
    }
}
