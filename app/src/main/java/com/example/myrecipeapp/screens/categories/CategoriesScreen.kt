package com.example.myrecipeapp.screens.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myrecipeapp.R
import com.example.myrecipeapp.components.ListContainer
import com.example.myrecipeapp.viewmodel.CategoriesViewModel


@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel,
    modifier: Modifier = Modifier,
    onNavigateToMeals: (String) -> Unit
) {
    Column(modifier = modifier.padding(horizontal = 8.dp)) {
        Text(
            stringResource(R.string.categories_screen_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 4.dp, start = 8.dp, end = 8.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        CategoriesList(viewModel, onNavigateToMeals = onNavigateToMeals)
    }
}

@Composable
fun CategoriesList(
    viewModel: CategoriesViewModel,
    modifier: Modifier = Modifier,
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
        modifier = modifier,
        renderItem = { category ->
            CategoryItem(category, onNavigateToMeals)
        })

}