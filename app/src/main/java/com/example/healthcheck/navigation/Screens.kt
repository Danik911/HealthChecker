package com.example.healthcheck.navigation

import androidx.navigation.NavHostController
import com.example.healthcheck.util.Constants.OPTIONS_SCREEN
import com.example.healthcheck.util.Constants.PARAM_SCREEN
import com.example.healthcheck.util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate(route = OPTIONS_SCREEN){
            popUpTo(SPLASH_SCREEN){
                inclusive = true
            }
        }
    }
    val options: () -> Unit = {
        navController.navigate(route = PARAM_SCREEN)
    }
    val param: () -> Unit = {
        navController.navigate(route = "")
    }

}