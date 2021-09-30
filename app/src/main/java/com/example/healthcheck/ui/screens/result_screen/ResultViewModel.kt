package com.example.healthcheck.ui.screens.result_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcheck.data.models.InvalidParametersException
import com.example.healthcheck.util.Constants.RESULT_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
   savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _resultState = mutableStateOf(0f)
    val resultState: State<Float> = _resultState

    init {
        savedStateHandle.get<Float>(RESULT_ARGUMENT_KEY)?.let { result ->
            if (result.isNaN() || result < 1) {
               throw InvalidParametersException(" Invalid parameters")
            } else {
                viewModelScope.launch {
                    _resultState.value = result
                }
            }
        }
    }
}



