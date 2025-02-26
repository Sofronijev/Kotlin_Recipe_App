package com.example.myrecipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myrecipeapp.network.KtorClient
import com.example.myrecipeapp.screens.categories.CategoriesScreen
import com.example.myrecipeapp.screens.categoryMeals.CategoryMealsScreen
import com.example.myrecipeapp.viewmodel.CategoriesViewModel

@Composable
fun AppNavigation(viewModel: CategoriesViewModel, modifier: Modifier = Modifier, ktorClient: KtorClient) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.CategoryScreen) {
        composable<Screen.CategoryScreen> {
            CategoriesScreen(viewModel, modifier, onNavigateToMeals = { categoryName ->
                navController.navigate(route = Screen.CategoryMeals(categoryName))
            })
        }
        composable<Screen.CategoryMeals> {
            val args = it.toRoute<Screen.CategoryMeals>()
            CategoryMealsScreen(modifier, ktorClient, args.categoryName)
        }
        composable<Screen.MealDetails> {

        }
    }
}
