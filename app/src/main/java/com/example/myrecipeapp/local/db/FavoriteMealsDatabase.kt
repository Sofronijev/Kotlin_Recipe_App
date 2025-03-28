package com.example.myrecipeapp.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myrecipeapp.local.dao.FavoriteMealDao
import com.example.myrecipeapp.local.entities.FavoriteMeal

@Database(
    entities = [FavoriteMeal::class],
    version = 1
)

abstract  class FavoriteMealsDatabase: RoomDatabase() {
    abstract  fun favoriteMealDao(): FavoriteMealDao
}