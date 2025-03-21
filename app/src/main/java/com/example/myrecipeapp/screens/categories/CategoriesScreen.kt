package com.example.myrecipeapp.screens.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.example.myrecipeapp.R
import com.example.myrecipeapp.components.Header
import com.example.myrecipeapp.components.ListContainer
import com.example.myrecipeapp.viewmodel.CategoriesViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel,
    bottomNavPadding: Dp,
    onNavigateToMeals: (String) -> Unit
) {
    Scaffold(
        topBar = {
            Header(text = stringResource(R.string.categories_screen_title))
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                // calculate Header padding
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            CategoriesList(viewModel, bottomNavPadding, onNavigateToMeals = onNavigateToMeals)
        }
    }

}

@Composable
fun CategoriesList(
    viewModel: CategoriesViewModel,
    bottomNavPadding: Dp,
    onNavigateToMeals: (String) -> Unit
) {
    val categories by viewModel.categories.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    ListContainer(
        data = categories,
        error = error,
        isLoading = isLoading,
        columns = 2,
        renderItem = { category ->
            CategoryItem(category, onNavigateToMeals)
        },
        bottomNavPadding = bottomNavPadding
    )

}