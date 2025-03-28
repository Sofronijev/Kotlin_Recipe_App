package com.example.myrecipeapp.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class FavoriteMeal(
    // 0 is used as the default value to indicate an uninitialized ID. Room will automatically generate a unique ID during insert, replacing the 0.
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val idMeal: Int,
    val strMeal: String,
    val strMealThumb: String,
)