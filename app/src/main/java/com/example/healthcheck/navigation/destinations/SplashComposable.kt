package com.example.healthcheck.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.example.healthcheck.ui.screens.spalsh.SplashScreen
import com.example.healthcheck.util.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToOptionScreen: () -> Unit
) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = { _, _ ->
            slideOutVertically(
                targetOffsetY = {fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 300)
            )
        }
    )
    {
        SplashScreen(navigateToOptionsScreen = navigateToOptionScreen)
    }
}