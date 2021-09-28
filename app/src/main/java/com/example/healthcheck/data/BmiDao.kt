package com.example.healthcheck.data

import androidx.room.*
import com.example.healthcheck.data.models.BmiResult
import kotlinx.coroutines.flow.Flow


@Dao
interface BmiDao {

    @Query("SELECT * FROM bmi_table ORDER BY timestamp ASC")
    fun getAllBmiResults(): Flow<List<BmiResult>>

    @Query("SELECT * FROM bmi_table WHERE bmiId= :bmiId")
    fun getSelectedBmiResult(bmiId: Int): Flow<BmiResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBmiResult(bmiResult: BmiResult)

    @Delete
    suspend fun deleteBmiResult(bmiResult: BmiResult)

    @Query("DELETE FROM bmi_table")
    suspend fun deleteAllBmiResults()


}