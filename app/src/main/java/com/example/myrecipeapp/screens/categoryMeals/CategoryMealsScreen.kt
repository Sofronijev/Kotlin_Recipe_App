package com.example.myrecipeapp.screens.categoryMeals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myrecipeapp.components.Header
import com.example.myrecipeapp.components.ListContainer
import com.example.myrecipeapp.network.KtorClient
import com.example.myrecipeapp.viewmodel.CategoryMealsViewModel

@Composable
fun CategoryMealsScreen(
    ktorClient: KtorClient,
    categoryName: String,
    navigateToMeal: (mealId: Int) -> Unit,
    backToCategories: () -> Unit,
) {
    // This is the way for the @Composable
    // Required to manually provide dependencies since ViewModel constructors cannot take parameters directly
    val extras = MutableCreationExtras().apply {
        // Ensures ktorClient is available inside ViewModel without needing a global provider like Hilt
        set(CategoryMealsViewModel.KTOR_CLIENT_KEY, ktorClient)
        // Allows passing dynamic data (categoryName) without relying on ViewModel arguments from a navigation system
        set(CategoryMealsViewModel.CATEGORY_NAME_KEY, categoryName)
    }

    // Needed because the default viewModel() function doesn’t support passing dependencies directly
    val viewModel: CategoryMealsViewModel = viewModel(
        factory = CategoryMealsViewModel.Factory, // Custom factory is required since ViewModel has constructor parameters
        extras = extras, // Supplies the necessary dependencies at creation time
    )


    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val meals by viewModel.meals.collectAsState()

    Scaffold(
        topBar = { Header(text = categoryName, onBack = backToCategories) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding(), bottom = 16.dp)
        ) {
            ListContainer(
                data = meals,
                error = error,
                isLoading = isLoading,
                renderItem = { meal ->
                    MealItem(meal, navigateToMeal)
                })
        }
    }

}