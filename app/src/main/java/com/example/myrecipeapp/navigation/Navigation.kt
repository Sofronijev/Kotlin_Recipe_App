package com.example.myrecipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.myrecipeapp.network.KtorClient
import com.example.myrecipeapp.screens.categories.CategoriesScreen
import com.example.myrecipeapp.screens.categoryMeals.CategoryMealsScreen
import com.example.myrecipeapp.screens.favorites.FavoritesScreen
import com.example.myrecipeapp.screens.mealDetails.MealDetailsScreen
import com.example.myrecipeapp.viewmodel.CategoriesViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: CategoriesViewModel,
    modifier: Modifier = Modifier,
    ktorClient: KtorClient
) {
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
                },
                backToCategories = { navController.popBackStack() }
            )

        }
        composable<NavRoutes.MealDetails> {
            val args = it.toRoute<NavRoutes.MealDetails>()
            MealDetailsScreen(
                modifier,
                ktorClient,
                mealId = args.mealId,
                backToMeals = { navController.popBackStack() }
            )
        }
        composable<NavRoutes.FavoriteScreen> {
//            CategoriesScreen(viewModel, modifier, onNavigateToMeals = { categoryName ->
//                navController.navigate(route = NavRoutes.CategoryMeals(categoryName))
//            })
            FavoritesScreen()
        }
    }
}
