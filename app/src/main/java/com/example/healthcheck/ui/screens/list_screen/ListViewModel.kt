package com.example.healthcheck.ui.screens.list_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcheck.data.models.BmiMeasurement
import com.example.healthcheck.data.models.Diagnosis
import com.example.healthcheck.data.repositories.BmiResultRepository
import com.example.healthcheck.data.repositories.DataStoreRepository
import com.example.healthcheck.util.Event
import com.example.healthcheck.util.RequestState
import com.example.healthcheck.util.SortOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: BmiResultRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    val event: MutableState<Event> = mutableStateOf(Event.NO_EVENT)

    val bmiId: MutableState<Int> = mutableStateOf(0)
    val timestamp: MutableState<Long> = mutableStateOf(0L)
    val diagnosis: MutableState<Diagnosis> = mutableStateOf(Diagnosis.NormalWeight)
    val bmiIndex: MutableState<Float> = mutableStateOf(0f)

    private val _allBmiMeasurements =
        MutableStateFlow<RequestState<List<BmiMeasurement>>>(RequestState.Idle)
    val allBmiMeasurement: StateFlow<RequestState<List<BmiMeasurement>>> = _allBmiMeasurements

    private val  _sortState =
        MutableStateFlow<RequestState<SortOrder>>(RequestState.Idle)
    val sortState: StateFlow<RequestState<SortOrder>> = _sortState

    init {
        getAllBmiMeasurements()
        readSortState()
    }

    private fun readSortState(){
        _sortState.value = RequestState.Loading
        try {
            viewModelScope.launch {
                dataStoreRepository.readSortState
                    .map { SortOrder.valueOf(it) }
                    .collect {
                        _sortState.value = RequestState.Success(it)
                    }
            }
        } catch (e: Exception){
            _sortState.value = RequestState.Error(error = e)
        }
    }
    fun persistSortState(sortOrder: SortOrder){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.persistSortState(sortOrder = sortOrder)
        }
    }

    val bmiSortedByIndex: StateFlow<List<BmiMeasurement>> =
        repository.getBmiSortedByIndexAsc.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val bmiSortedByDate: StateFlow<List<BmiMeasurement>> =
        repository.getBmiSortedByDateAsc.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )


    fun getAllBmiMeasurements() {
        _allBmiMeasurements.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllBmiResults.collect {
                    _allBmiMeasurements.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allBmiMeasurements.value = RequestState.Error(error = e)
        }
    }

    fun deleteAllBmiMeasurements() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllBmiResults()
        }
    }

    fun deleteBmiMeasurement() {
        viewModelScope.launch {
            val bmiMeasurement = BmiMeasurement(
                bmiId = bmiId.value,
                timestamp = timestamp.value,
                diagnosis = diagnosis.value,
                bmiIndex = bmiIndex.value
            )
            repository.deleteBmiResult(bmiMeasurement = bmiMeasurement)
        }
    }

    private fun undoDeleteBmiMeasurement() {
        viewModelScope.launch(Dispatchers.IO) {

            val bmiMeasurement = BmiMeasurement(
                timestamp = timestamp.value,
                diagnosis = diagnosis.value,
                bmiIndex = bmiIndex.value
            )

            repository.addBmiResult(bmiMeasurement = bmiMeasurement)
        }
    }

    fun handleEvent(event: Event) {
        when (event) {
            Event.DELETE -> {
                deleteBmiMeasurement()
            }
            Event.UNDO -> {
                undoDeleteBmiMeasurement()
            }
            Event.UPDATE -> {

            }
            Event.ADD -> {

            }
            Event.DELETE_ALL -> {
                deleteAllBmiMeasurements()
            }
            else -> {

            }
        }

    }

    fun updateBmiMeasurementFields(currentBmiMeasurement: BmiMeasurement){
        bmiId.value = currentBmiMeasurement.bmiId
        timestamp.value = currentBmiMeasurement.timestamp
        bmiIndex.value = currentBmiMeasurement.bmiIndex
        diagnosis.value = currentBmiMeasurement.diagnosis
    }

}