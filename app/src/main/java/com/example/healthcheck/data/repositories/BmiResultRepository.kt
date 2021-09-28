package com.example.healthcheck.data.repositories

import com.example.healthcheck.data.BmiDao
import com.example.healthcheck.data.models.BmiResult
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class BmiResultRepository @Inject constructor(private val bmiDao: BmiDao){

    val getAllBmiResults: Flow<List<BmiResult>> = bmiDao.getAllBmiResults()

    fun getSelectedBmiResult(bmiId: Int): Flow<BmiResult>{
        return bmiDao.getSelectedBmiResult(bmiId = bmiId)
        }
    suspend fun addBmiResult(bmiResult: BmiResult){
        bmiDao.addBmiResult(bmiResult = bmiResult)
        }
    suspend fun deleteBmiResult(bmiResult: BmiResult){
        bmiDao.deleteBmiResult(bmiResult = bmiResult)
    }
    suspend fun deleteAllBmiResults(){
        bmiDao.deleteAllBmiResults()
    }

}