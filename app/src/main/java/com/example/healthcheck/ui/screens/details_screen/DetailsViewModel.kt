package com.example.healthcheck.ui.screens.details_screen

import androidx.lifecycle.ViewModel
import com.example.healthcheck.data.models.BmiMeasurement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (): ViewModel(){

    private val _selectedBmiMeasurement: MutableStateFlow<BmiMeasurement?>
    = MutableStateFlow(null)
    val selectedBmiMeasurement: StateFlow<BmiMeasurement?> = _selectedBmiMeasurement

    fun getSelectedBmiResult(bmiId: Int){

    }

}