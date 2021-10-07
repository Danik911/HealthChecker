package com.example.healthcheck.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.example.healthcheck.ui.screens.details_screen.DetailScreen
import com.example.healthcheck.ui.screens.details_screen.DetailsViewModel
import com.example.healthcheck.util.Constants.DETAILS_ARGUMENT_KEY
import com.example.healthcheck.util.Constants.DETAILS_SCREEN
import com.example.healthcheck.util.Event
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.detailsComposable(
   viewModel: DetailsViewModel,
    navigateToListScreen: (Event) -> Unit
) {
    composable(
        route = DETAILS_SCREEN,
        arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
            type = NavType.IntType
        }),
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = {fullWidth -> -fullWidth },
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }
    ) { navBackStackEntry ->
        val bmiId = navBackStackEntry.arguments!!.getInt(DETAILS_ARGUMENT_KEY)
        LaunchedEffect(key1 = bmiId){
            viewModel.getSelectedBmiResult(bmiId = bmiId)
        }
        val selectedBmiMeasurement by viewModel.selectedBmiMeasurement.collectAsState()

        LaunchedEffect(key1 = selectedBmiMeasurement) {
            if (selectedBmiMeasurement != null || bmiId == -1) {
                //sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }

        DetailScreen()
    }
}