package com.example.healthcheck.ui.screens.result_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcheck.data.models.BmiMeasurement
import com.example.healthcheck.data.models.Diagnosis
import com.example.healthcheck.data.repositories.BmiResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BmiResultRepository
) : ViewModel() {

    private val _latestBmiMeasurement = mutableStateOf(
        BmiMeasurement(
            0,
            "",
            0L,
            diagnosis = Diagnosis.NormalWeight,
            0f
        )
    )
    val latestBmiMeasurement: State<BmiMeasurement> = _latestBmiMeasurement


    fun getTheLatestTask() {
        viewModelScope.launch {
            repository.getTheLatestBmiMeasurement.collect { bmiMeasurement ->
                _latestBmiMeasurement.value = bmiMeasurement
            }
        }

    }
}



