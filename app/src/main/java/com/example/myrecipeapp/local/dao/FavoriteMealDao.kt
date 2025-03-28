package com.example.myrecipeapp.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myrecipeapp.local.entities.FavoriteMeal
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMealDao {
    @Query("SELECT id, idMeal, strMeal, strMealThumb FROM meals")
    fun fetchFavoriteMeals(): Flow<List<FavoriteMeal>>

    @Delete
    suspend fun removeFavoriteMeal(meal: FavoriteMeal)

    // Custom query to delete by id
    @Query("DELETE FROM meals WHERE idMeal = :mealId")
    suspend fun removeFavoriteMealById(mealId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMeal(vararg meal: FavoriteMeal)

    @Query("SELECT EXISTS(SELECT 1 FROM meals WHERE idMeal = CAST(:mealId AS INTEGER))")
    fun isMealFavorite(mealId: String): Flow<Boolean>
}