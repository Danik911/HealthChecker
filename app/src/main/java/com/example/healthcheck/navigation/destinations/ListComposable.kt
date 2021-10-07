package com.example.healthcheck.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.example.healthcheck.ui.screens.list_screen.ListScreen
import com.example.healthcheck.ui.screens.result_screen.ResultScreen
import com.example.healthcheck.util.Constants
import com.example.healthcheck.util.Constants.LIST_ARGUMENT_KEY
import com.example.healthcheck.util.Event
import com.example.healthcheck.util.toEvent
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToBmiMeasurement: (bmiId: Int) -> Unit
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        }),

        enterTransition = {_, _ ->
            slideInHorizontally(
                initialOffsetX = {fullWidth -> fullWidth },
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }


    ) { navBackStackEntry ->

        val event = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toEvent()
        LaunchedEffect(key1 = event){

        }

        ListScreen(navigateToBmiMeasurement = navigateToBmiMeasurement)
    }
}