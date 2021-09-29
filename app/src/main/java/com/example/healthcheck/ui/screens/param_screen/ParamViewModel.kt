package com.example.healthcheck.ui.screens.param_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ParamViewModel @Inject constructor (): ViewModel(){

    val highState: MutableState<String> = mutableStateOf("")
    val weightState: MutableState<String> = mutableStateOf("")


}