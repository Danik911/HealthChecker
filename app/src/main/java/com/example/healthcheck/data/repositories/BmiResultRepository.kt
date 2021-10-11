package com.example.healthcheck.data.repositories

import com.example.healthcheck.data.BmiDao
import com.example.healthcheck.data.models.BmiMeasurement
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class BmiResultRepository @Inject constructor(private val bmiDao: BmiDao){

    val getAllBmiResults: Flow<List<BmiMeasurement>> = bmiDao.getAllBmiResults()
    val getBmiSortedByIndexAsc: Flow<List<BmiMeasurement>> = bmiDao.getBmiSortedByIndexAsc()
    val getBmiSortedByDateAsc: Flow<List<BmiMeasurement>> = bmiDao.getBmiSortedByDateAsc()

    val getTheLatestBmiMeasurement: Flow<BmiMeasurement> = bmiDao.getTheLatestBmiMeasurement()

    fun getSelectedBmiResult(bmiId: Int): Flow<BmiMeasurement>{
        return bmiDao.getSelectedBmiResult(bmiId = bmiId)
        }
    suspend fun addBmiResult(bmiMeasurement: BmiMeasurement){
        bmiDao.addBmiResult(bmiMeasurement = bmiMeasurement)
        }
    suspend fun deleteBmiResult(bmiMeasurement: BmiMeasurement){
        bmiDao.deleteBmiResult(bmiMeasurement = bmiMeasurement)
    }
    suspend fun deleteAllBmiResults(){
        bmiDao.deleteAllBmiResults()
    }


}