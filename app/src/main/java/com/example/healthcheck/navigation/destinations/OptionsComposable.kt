package com.example.healthcheck.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import com.example.healthcheck.ui.screens.options_screen.OptionsScreen
import com.example.healthcheck.util.Constants.OPTIONS_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.optionComposable(
    navigateToParamScreen: () -> Unit,

    ) {
    composable(
        route = OPTIONS_SCREEN,
    ) {
        OptionsScreen(
            navigateToParamScreen = navigateToParamScreen
        )
    }
}