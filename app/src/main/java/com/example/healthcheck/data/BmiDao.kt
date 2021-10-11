package com.example.healthcheck.data

import androidx.room.*
import com.example.healthcheck.data.models.BmiMeasurement
import kotlinx.coroutines.flow.Flow


@Dao
interface BmiDao {

    @Query("SELECT * FROM bmi_table ORDER BY timestamp ASC")
    fun getAllBmiResults(): Flow<List<BmiMeasurement>>

    @Query("SELECT * FROM bmi_table WHERE bmiId= :bmiId")
    fun getSelectedBmiResult(bmiId: Int): Flow<BmiMeasurement>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBmiResult(bmiMeasurement: BmiMeasurement)

    @Delete
    suspend fun deleteBmiResult(bmiMeasurement: BmiMeasurement)

    @Query("DELETE FROM bmi_table")
    suspend fun deleteAllBmiResults()

    @Query("SELECT * FROM bmi_table ORDER BY timestamp DESC LIMIT 1")
    fun getTheLatestBmiMeasurement(): Flow<BmiMeasurement>

    @Query("SELECT * FROM bmi_table ORDER BY bmiIndex")
    fun getBmiSortedByIndexAsc(): Flow<List<BmiMeasurement>>

    @Query("SELECT * FROM bmi_table ORDER BY timestamp")
    fun getBmiSortedByDateAsc(): Flow<List<BmiMeasurement>>



}