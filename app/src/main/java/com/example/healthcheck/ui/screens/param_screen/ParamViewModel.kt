package com.example.healthcheck.ui.screens.param_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcheck.data.models.BmiMeasurement
import com.example.healthcheck.data.models.Diagnosis
import com.example.healthcheck.data.repositories.BmiResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ParamViewModel @Inject constructor(
    private val repository: BmiResultRepository
) : ViewModel() {

    val highState: MutableState<String> = mutableStateOf("")
    val weightState: MutableState<String> = mutableStateOf("")


    val bmiResult: MutableState<Float> = mutableStateOf(0f)

    private val _currentMeasurement: MutableStateFlow<BmiMeasurement> =
        MutableStateFlow(BmiMeasurement(
            0,
            0L,
            Diagnosis.NormalWeight,
            0f
        ))
    val currentMeasurement: StateFlow<BmiMeasurement> = _currentMeasurement

    fun calculateBmiIndex()  {

        bmiResult.value =
            weightState.value.toFloat() / (highState.value.toFloat() * highState.value.toFloat())

    }
    private fun getDiagnosis(): Diagnosis{
        return when(bmiResult.value){
            in (1.0..16.4) -> {
                Diagnosis.SevereUnderWeight
            }
            in (16.5..18.5) -> {
                Diagnosis.UnderWeight
            }
            in (18.6..25.0) -> {
                Diagnosis.NormalWeight
            }
            in (25.1..30.0) -> {
                Diagnosis.OverWeight
            }
            in (30.1..35.0) -> {
                Diagnosis.ObesityFirstStage
            }
            in (35.1..40.0) -> {
                Diagnosis.ObesitySecondStage
            }
            in (40.1..50.0) -> {
                Diagnosis.ObesityFirstStage
            }
            else -> Diagnosis.ObesityThirdStage
        }
    }


    fun addBmiMeasurement() {
        viewModelScope.launch(Dispatchers.IO) {
            val diagnosis = getDiagnosis()
            _currentMeasurement.value = BmiMeasurement(
                timestamp = System.currentTimeMillis(),
                diagnosis = diagnosis,
                bmiIndex = bmiResult.value
            )

            repository.addBmiResult(bmiMeasurement = _currentMeasurement.value)
        }
    }
    fun resetBmiMeasurement(){
       highState.value = ""
        weightState.value = ""
    }
}
