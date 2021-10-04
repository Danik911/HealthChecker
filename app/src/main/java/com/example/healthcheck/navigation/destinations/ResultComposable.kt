package com.example.healthcheck.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.material.ExperimentalMaterialApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.example.healthcheck.ui.screens.result_screen.ResultScreen
import com.example.healthcheck.ui.screens.result_screen.ResultViewModel
import com.example.healthcheck.util.Constants
import com.example.healthcheck.util.Constants.RESULT_ARGUMENT_KEY
import com.example.healthcheck.util.Constants.RESULT_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.resultComposable(
    navigateToHomeScreen: () -> Unit
    ) {
    composable(
        route = RESULT_SCREEN,
        arguments = listOf(navArgument(RESULT_ARGUMENT_KEY){
            type = NavType.LongType
        }),
        enterTransition = {_, _ ->
            slideInHorizontally(
                initialOffsetX = {fullWidth -> fullWidth },
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }


    ) {
        //navBackStackEntry ->
        //val bmiMeasurementId = navBackStackEntry.arguments!!.getInt
        ResultScreen (navigateToHomeScreen = navigateToHomeScreen)

    }
}