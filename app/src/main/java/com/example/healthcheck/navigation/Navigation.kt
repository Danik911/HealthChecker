package com.example.healthcheck.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.healthcheck.navigation.destinations.*
import com.example.healthcheck.ui.screens.list_screen.ListViewModel
import com.example.healthcheck.util.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
    listViewModel: ListViewModel = hiltViewModel()

    ) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(navigateToOptionScreen = screen.toOptions)
        optionComposable(
            navigateToParamScreen = screen.toParam,
            navigateToListScreen = screen.toList
        )
        paramComposable(
            navigateToResultScreen = screen.toResult,
            navigateToHomeScreen = screen.toOptions
        )
        resultComposable(
            navigateToHomeScreen = screen.toOptions,
            navigateToListScreen = screen.toList
        )
        listComposable(
            navigateToBmiMeasurement = screen.toDetails,
            navigateToHome = screen.toOptions,
            viewModel = listViewModel
        )
    }
}