package com.example.healthcheck.ui.screens.list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcheck.data.models.BmiMeasurement
import com.example.healthcheck.data.repositories.BmiResultRepository
import com.example.healthcheck.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: BmiResultRepository
): ViewModel() {
    private val _allBmiMeasurements =
        MutableStateFlow<RequestState<List<BmiMeasurement>>>(RequestState.Idle)
    val allBmiMeasurement: StateFlow<RequestState<List<BmiMeasurement>>> = _allBmiMeasurements

    fun getAllBmiMeasurements(){
        _allBmiMeasurements.value = RequestState.Loading
        try{
            viewModelScope.launch {
                repository.getAllBmiResults.collect {
                    _allBmiMeasurements.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception){
            _allBmiMeasurements.value = RequestState.Error(error = e)
        }
    }
}