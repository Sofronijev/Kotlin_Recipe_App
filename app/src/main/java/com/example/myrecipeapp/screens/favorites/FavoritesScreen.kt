package com.example.myrecipeapp.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.NoFood
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myrecipeapp.R
import com.example.myrecipeapp.components.Header
import com.example.myrecipeapp.model.Meal
import com.example.myrecipeapp.screens.categoryMeals.MealItem
import com.example.myrecipeapp.viewmodel.FavoriteViewModel

@Composable
fun FavoritesScreen(
    favoriteViewModel: FavoriteViewModel,
    bottomNavPadding: Dp,
    navigateToMeal: (mealId: Int) -> Unit,
) {
    val isLoading by favoriteViewModel.isLoading.collectAsState()
    val favoriteMeals by favoriteViewModel.favoriteMeals.collectAsState()

    Scaffold(
        topBar = {
            Header(text = stringResource(R.string.favorite_meals))
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
                    )
                }

                favoriteMeals.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.TwoTone.NoFood,
                                contentDescription = "Back",
                                modifier = Modifier.size(100.dp),
                            )
                        }
                        Text(
                            stringResource(R.string.favorite_screen_null),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                    }
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp,
                            bottom = bottomNavPadding + 16.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(favoriteMeals) { meal ->
                            val formatedMeal = Meal(meal.idMeal, meal.strMeal, meal.strMealThumb)
                            MealItem(formatedMeal, navigateToMeal)
                        }
                    }
                }
            }
        }
    }


}