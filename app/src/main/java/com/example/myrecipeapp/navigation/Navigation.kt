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
import com.example.myrecipeapp.screens.mealDetails.MealDetailsScreen
import com.example.myrecipeapp.viewmodel.CategoriesViewModel

@Composable
fun AppNavigation(
    viewModel: CategoriesViewModel,
    modifier: Modifier = Modifier,
    ktorClient: KtorClient
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.CategoryScreen) {
        composable<NavRoutes.CategoryScreen> {
            CategoriesScreen(viewModel, modifier, onNavigateToMeals = { categoryName ->
                navController.navigate(route = NavRoutes.CategoryMeals(categoryName))
            })
        }
        composable<NavRoutes.CategoryMeals> {
            val args = it.toRoute<NavRoutes.CategoryMeals>()
            CategoryMealsScreen(
                modifier,
                ktorClient,
                args.categoryName,
                navigateToMeal = { mealId ->
                    navController.navigate(route = NavRoutes.MealDetails(mealId))
                })
        }
        composable<NavRoutes.MealDetails> {
            val args = it.toRoute<NavRoutes.MealDetails>()
            MealDetailsScreen(modifier, ktorClient, mealId = args.mealId)
        }
    }
}
