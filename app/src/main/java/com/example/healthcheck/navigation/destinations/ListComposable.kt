package com.example.healthcheck.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.example.healthcheck.ui.screens.list_screen.ListScreen
import com.example.healthcheck.ui.screens.result_screen.ResultScreen
import com.example.healthcheck.util.Constants
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(

) {
    composable(
        route = Constants.LIST_SCREEN,

        enterTransition = {_, _ ->
            slideInHorizontally(
                initialOffsetX = {fullWidth -> fullWidth },
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }


    ) {
        ListScreen()
    }
}