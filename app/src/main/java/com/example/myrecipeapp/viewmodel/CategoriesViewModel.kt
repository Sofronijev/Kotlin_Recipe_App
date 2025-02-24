package com.example.myrecipeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipeapp.model.MealCategory
import com.example.myrecipeapp.network.KtorClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel(private val ktorClient: KtorClient) : ViewModel() {
    private val _categories = MutableStateFlow<List<MealCategory>>(emptyList())
    private val _isLoading = MutableStateFlow<Boolean>(false)
    private val _error = MutableStateFlow<String>("")

    val categories: StateFlow<List<MealCategory>> = _categories
    val isLoading: StateFlow<Boolean> = _isLoading
    val error: StateFlow<String> = _error

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _categories.value = ktorClient.getMealCategories()
            } catch (e: Exception) {
                _error.value = "Error fetching data"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
