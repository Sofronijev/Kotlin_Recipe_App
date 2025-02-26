package com.example.myrecipeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myrecipeapp.model.Meal
import com.example.myrecipeapp.network.KtorClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryMealsViewModel(private val ktorClient: KtorClient, categoryName: String) : ViewModel() {
    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    private val _isLoading = MutableStateFlow<Boolean>(false)
    private val _error = MutableStateFlow<String>("")

    val meals: StateFlow<List<Meal>> = _meals
    val isLoading: StateFlow<Boolean> = _isLoading
    val error: StateFlow<String> = _error

    init {
        fetchMealsByCategory(categoryName)
    }

    private fun fetchMealsByCategory(categoryName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _meals.value = ktorClient.getMealsByCategory(categoryName)
            } catch (e: Exception) {
                _error.value = "Error fetching meals"
            } finally {
                _isLoading.value = false
            }
        }
    }


    companion object {
        // Define a custom key for your dependency
        val KTOR_CLIENT_KEY = object : CreationExtras.Key<KtorClient> {}
        val CATEGORY_NAME_KEY = object : CreationExtras.Key<String> {}

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Get the dependency in your factory
                val ktorClient = this[KTOR_CLIENT_KEY] as KtorClient
                val name = this[CATEGORY_NAME_KEY] as String
                CategoryMealsViewModel(
                    ktorClient = ktorClient,
                    categoryName = name,
                )
            }
        }
    }
}
