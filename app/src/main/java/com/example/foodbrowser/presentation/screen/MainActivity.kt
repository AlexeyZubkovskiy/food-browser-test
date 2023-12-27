package com.example.foodbrowser.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.foodbrowser.presentation.navigation.MainNavigation
import com.example.foodbrowser.presentation.theme.FoodBrowserTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodBrowserTheme {
                val navController = rememberNavController()
                Scaffold { paddingValues ->
                    MainNavigation(
                        navHost = navController,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }

}