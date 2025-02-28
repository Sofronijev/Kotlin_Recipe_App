package com.example.myrecipeapp.screens.mealDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myrecipeapp.R
import com.example.myrecipeapp.network.KtorClient
import com.example.myrecipeapp.viewmodel.MealViewModel

@Composable
fun MealDetailsScreen(modifier: Modifier, ktorClient: KtorClient, mealId: Int) {
    val extras = MutableCreationExtras().apply {
        set(MealViewModel.KTOR_CLIENT_KEY, ktorClient)
        set(MealViewModel.MEAL_ID_KEY, mealId)
    }

    val viewModel: MealViewModel = viewModel(
        factory = MealViewModel.Factory,
        extras = extras
    )

    val meal by viewModel.meal.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

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
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    error,
                    modifier.wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

        }

        meal == null -> {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(R.string.meal_not_found),
                    modifier.wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }

        else -> meal?.let {
            MealDetailsContainer(it, modifier)
        }
    }

}