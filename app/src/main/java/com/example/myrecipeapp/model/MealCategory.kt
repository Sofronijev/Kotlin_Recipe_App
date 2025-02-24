package com.example.myrecipeapp.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(
    val categories: List<MealCategory>
)

@Serializable
data class MealCategory(
    val idCategory: Int,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)
