package com.example.myrecipeapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun <T> ListContainer(
    data: List<T>,
    error: String,
    isLoading: Boolean,
    renderItem: @Composable (T) -> Unit,
    modifier: Modifier = Modifier,
    columns: Int = 1,
) {
    when {
        isLoading -> {
            CircularProgressIndicator(
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }

        error.isNotEmpty() -> {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    error,
                    modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

        }

        data.isEmpty() && !isLoading -> {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No categories found",
                    textAlign = TextAlign.Center,
                )
            }

        }

        else -> {

            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp)
            ) {
                items(data) { item ->
                    renderItem(item)
                }
            }
        }
    }
}