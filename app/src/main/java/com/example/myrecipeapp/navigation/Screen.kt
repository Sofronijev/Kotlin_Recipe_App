package com.example.myrecipeapp.navigation

import kotlinx.serialization.Serializable

sealed class Screen() {
    @Serializable
    data object CategoryScreen
    @Serializable
    data class CategoryMeals(val categoryName: String)
    @Serializable
    data class MealDetails(val mealId: Int)
}