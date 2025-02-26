package com.example.myrecipeapp.model

import kotlinx.serialization.Serializable


@Serializable
data class MealResponse(
    val meals: List<Meal>
)

@Serializable
data class Meal(
    val idMeal: Int,
    val strMeal: String,
    val strMealThumb: String,
)