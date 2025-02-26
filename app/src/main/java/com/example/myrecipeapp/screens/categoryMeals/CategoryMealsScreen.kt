package com.example.myrecipeapp.screens.categoryMeals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myrecipeapp.components.ListContainer
import com.example.myrecipeapp.network.KtorClient
import com.example.myrecipeapp.viewmodel.CategoryMealsViewModel

@Composable
fun CategoryMealsScreen(
    modifier: Modifier = Modifier,
    ktorClient: KtorClient,
    categoryName: String
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

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
            text = categoryName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 4.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        ListContainer(
            data = meals,
            error = error,
            isLoading = isLoading,
            modifier = modifier,
            renderItem = { meal ->
                MealItem(meal)
            })
    }

}