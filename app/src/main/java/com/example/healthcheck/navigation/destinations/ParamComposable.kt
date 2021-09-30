package com.example.healthcheck.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import com.example.healthcheck.ui.screens.param_screen.ParamScreen
import com.example.healthcheck.util.Constants
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.paramComposable(
    navigateToHomeScreen: () -> Unit,
    navigateToResultScreen: (Float) -> Unit

    ) {
    composable(
        route = Constants.PARAM_SCREEN,
    ) {
        ParamScreen(
            navigateToHome = navigateToHomeScreen,
            navigateToResultScreen = navigateToResultScreen
        )
    }
}