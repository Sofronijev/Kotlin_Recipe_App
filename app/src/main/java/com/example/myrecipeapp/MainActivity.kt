package com.example.myrecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.myrecipeapp.local.db.FavoriteMealsDatabase
import com.example.myrecipeapp.navigation.AppNavigation
import com.example.myrecipeapp.navigation.NavigationBottomBar
import com.example.myrecipeapp.network.KtorClient
import com.example.myrecipeapp.ui.theme.MyRecipeAppTheme
import com.example.myrecipeapp.viewmodel.CategoriesViewModel
import com.example.myrecipeapp.viewmodel.FavoriteViewModel


class MainActivity : ComponentActivity() {
    private val ktorClient = KtorClient()


    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            FavoriteMealsDatabase::class.java, "favorite-meals"
        ).build()
    }

    // Automatically creates and provides a ViewModel instance
    private val viewModel: CategoriesViewModel by viewModels {
        // Uses a factory to create the ViewModel since it requires dependencies
        viewModelFactory {
            // Defines how to initialize the ViewModel (passes ktorClient)
            initializer { CategoriesViewModel(ktorClient) }
        }
    }

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        // Uses a factory to create the ViewModel since it requires dependencies
        viewModelFactory {
            // Defines how to initialize the ViewModel (passes ktorClient)
            initializer { FavoriteViewModel(db.favoriteMealDao()) }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyRecipeAppTheme {
                App(
                    viewModel,
                    ktorClient,
                    favoriteViewModel
                )
            }
        }
    }
}


@Composable
fun App(
    viewModel: CategoriesViewModel,
    ktorClient: KtorClient,
    favoriteViewModel: FavoriteViewModel
) {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        NavigationBottomBar(navController)
    }, modifier = Modifier.fillMaxSize()) { innerPadding ->
        AppNavigation(
            navController,
            viewModel,
            // Calculate NavigationBottomBar padding, for top it is calculated in screen that use Header
            bottomNavPadding = innerPadding.calculateBottomPadding(),
            ktorClient,
            favoriteViewModel
        )

    }
}

