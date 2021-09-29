package com.example.healthcheck.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.healthcheck.ui.screens.options_screen.OptionsScreen
import com.example.healthcheck.util.Constants.OPTIONS_SCREEN

fun NavGraphBuilder.optionsComposable(navigateToParamScreen: () -> Unit

){
    composable(route = OPTIONS_SCREEN){
        OptionsScreen(navigateToParamScreen = navigateToParamScreen)

    }
}