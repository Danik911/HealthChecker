package com.example.healthcheck.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.healthcheck.navigation.destinations.optionsComposable
import com.example.healthcheck.navigation.destinations.splashComposable
import com.example.healthcheck.util.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController){

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ){
        splashComposable (navigateToOptionsScreen = screen.splash)
        optionsComposable(navigateToParamScreen = screen.options)
    }
}