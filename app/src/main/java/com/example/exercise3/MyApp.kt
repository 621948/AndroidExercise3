package com.example.exercise3

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exercise3.ui.screens.AnimatedSpecificScreen
import com.example.exercise3.ui.screens.HomeScreen
import com.example.exercise3.ui.screens.TimerScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.serialization.Serializable

@HiltAndroidApp
class MyApp : Application()

@AndroidEntryPoint // Add this to the MainActivity in order for Hilt to magically inject your classes
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Serializable
data object Home

@Serializable
data object Timer

@Serializable
data object AnimatedSpecific

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        enterTransition = { slideInHorizontally() },
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                onNavigateToTimer = { navController.navigate(Timer) },
                onNavigateToAnimatedSpecific = { navController.navigate(AnimatedSpecific) }
            )
        }

        composable<AnimatedSpecific> ( enterTransition = { fadeIn() + slideInHorizontally() } ) {
            AnimatedSpecificScreen(
                onNavigateToHome = { navController.navigate(Home) {
                    launchSingleTop = true
                    restoreState = true
                } }
            )
        }

        composable<Timer> {
            TimerScreen(
                onNavigateToHome = { navController.navigate(Home) {
                    launchSingleTop = true
                    restoreState = true
                } }
            )
        }
    }
}