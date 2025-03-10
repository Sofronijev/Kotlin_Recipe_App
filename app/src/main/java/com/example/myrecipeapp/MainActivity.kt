package com.example.myrecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myrecipeapp.navigation.AppNavigation
import com.example.myrecipeapp.network.KtorClient
import com.example.myrecipeapp.ui.theme.MyRecipeAppTheme
import com.example.myrecipeapp.viewmodel.CategoriesViewModel


class MainActivity : ComponentActivity() {
    private val ktorClient = KtorClient()

    // Automatically creates and provides a ViewModel instance
    private val viewModel: CategoriesViewModel by viewModels {
        // Uses a factory to create the ViewModel since it requires dependencies
        viewModelFactory {
            // Defines how to initialize the ViewModel (passes ktorClient)
            initializer { CategoriesViewModel(ktorClient) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyRecipeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(viewModel, modifier = Modifier.padding(innerPadding), ktorClient)
                }
            }
        }
    }
}



