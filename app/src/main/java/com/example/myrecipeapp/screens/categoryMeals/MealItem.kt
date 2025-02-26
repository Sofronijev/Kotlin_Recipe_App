package com.example.myrecipeapp.screens.categoryMeals

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myrecipeapp.model.Meal

@Composable
fun MealItem(meal: Meal) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp,
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = "${meal.strMeal} image",
                modifier = Modifier.height(100.dp),
            )

            Text(
                text = meal.strMeal,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 20.sp

            )

        }
    }

}