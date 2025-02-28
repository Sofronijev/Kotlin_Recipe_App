package com.example.myrecipeapp.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.myrecipeapp.model.MealCategory
import com.example.myrecipeapp.ui.theme.Brown

@Composable
fun CategoryItem(item: MealCategory, onNavigateToMeals: (String) -> Unit) {

    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp,
        ),

        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable { onNavigateToMeals(item.strCategory) }
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Brown.copy(alpha = 0.5f)),
                        startY = 20f // Adjust fade start position
                    )
                )

        ) {
            SubcomposeAsyncImage(
                model = item.strCategoryThumb,
                contentDescription = "${item.strCategory} image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(),
                        color = Brown

                    )
                }
            )

            Text(
                text = item.strCategory,
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color(0xFF795548).copy(alpha = 0.7f))
                    .padding(8.dp)
            )

        }
    }


}