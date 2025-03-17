package com.example.myrecipeapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.myrecipeapp.ui.theme.Brown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(text: String = "", onBack: (() -> Unit)? = null) {
    CenterAlignedTopAppBar(
        colors = topAppBarColors(
            containerColor = Brown,
            titleContentColor = Color.White,
        ),
        title = {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }

        }
    )
}