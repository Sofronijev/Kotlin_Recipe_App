package com.example.myrecipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
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
import com.example.myrecipeapp.viewmodel.FavoriteViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: CategoriesViewModel,
    bottomNavPadding: Dp,
    ktorClient: KtorClient,
    favoriteViewModel: FavoriteViewModel
) {
    NavHost(navController = navController, startDestination = NavRoutes.CategoryScreen) {
        composable<NavRoutes.CategoryScreen> {
            CategoriesScreen(viewModel, bottomNavPadding, onNavigateToMeals = { categoryName ->
                navController.navigate(route = NavRoutes.CategoryMeals(categoryName))
            })
        }
        composable<NavRoutes.CategoryMeals> {
            val args = it.toRoute<NavRoutes.CategoryMeals>()
            CategoryMealsScreen(
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
                ktorClient,
                favoriteViewModel,
                mealId = args.mealId,
                backToMeals = { navController.popBackStack() }
            )
        }
        composable<NavRoutes.FavoriteScreen> {
//            CategoriesScreen(viewModel, modifier, onNavigateToMeals = { categoryName ->
//                navController.navigate(route = NavRoutes.CategoryMeals(categoryName))
//            })
            FavoritesScreen(favoriteViewModel)
        }
    }
}
