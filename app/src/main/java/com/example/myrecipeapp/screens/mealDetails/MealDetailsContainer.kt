package com.example.myrecipeapp.screens.mealDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myrecipeapp.R
import com.example.myrecipeapp.components.Header
import com.example.myrecipeapp.components.YoutubeVideoPlayer
import com.example.myrecipeapp.model.MealDetail
import com.example.myrecipeapp.ui.theme.Brown

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MealDetailsContainer(
    meal: MealDetail, backToMeals: () -> Unit
) {

    Scaffold(
        topBar = { Header(text = stringResource(R.string.recipe), onBack = backToMeals) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (!meal.strYoutube.isNullOrBlank()) {
                YoutubeVideoPlayer(meal.strYoutube)
            } else {
                AsyncImage(
                    model = meal.strMealThumb,
                    contentDescription = "${meal.strMeal} image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.FillWidth
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    Text(
                        meal.strMeal,
                        modifier = Modifier
                            .padding(16.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                item {
                    if (meal.mealTags.isNotEmpty()) {
                        FlowRow(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            meal.mealTags.forEach { tag ->
                                Surface(
                                    color = Brown,
                                    shape = MaterialTheme.shapes.small,
                                ) {
                                    Text(
                                        text = tag,
                                        modifier = Modifier.padding(
                                            horizontal = 12.dp,
                                            vertical = 4.dp
                                        ),
                                        style = MaterialTheme.typography.labelLarge,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }


                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(meal.ingredients) { (measure, ingredient) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = measure,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = ingredient.replaceFirstChar { it.uppercase() },
                            modifier = Modifier.weight(2f)
                        )
                    }
                }
                item {
                    Text(
                        meal.strInstructions,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                    )
                }
            }
        }
    }

}