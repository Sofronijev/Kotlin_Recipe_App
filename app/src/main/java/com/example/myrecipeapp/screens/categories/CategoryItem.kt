package com.example.myrecipeapp.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myrecipeapp.model.MealCategory

@Composable
fun CategoryItem(item: MealCategory) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { }
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .height(150.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color(0xFF795548).copy(alpha = 0.5f)),
                    startY = 20f // Adjust fade start position
                )
            )

    ) {
        AsyncImage(
            model = item.strCategoryThumb,
            contentDescription = "${item.strCategory} image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )

        Text(
            text = item.strCategory,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
            color = Color.White, // Make text visible
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth() // Full width
                .align(Alignment.BottomStart) // Align to bottom
                .background(Color(0xFF795548).copy(alpha = 0.7f)) // Semi-transparent background
                .padding(8.dp) // Padding inside the text box
        )
    }



}