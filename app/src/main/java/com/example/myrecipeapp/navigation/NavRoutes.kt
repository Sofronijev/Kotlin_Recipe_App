package com.example.myrecipeapp.navigation

import kotlinx.serialization.Serializable

object NavRoutes {
    @Serializable
    data object CategoryScreen
    @Serializable
    data class CategoryMeals(val categoryName: String)
    @Serializable
    data class MealDetails(val mealId: Int)
}