package com.example.healthcheck.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.healthcheck.util.Constants.BMI_TABLE

@Entity(tableName = BMI_TABLE)
data class BmiMeasurement(
    @PrimaryKey(autoGenerate = true)
    val BmiId: Int = 0,
    val timestamp: Long,
    val diagnosis: Diagnosis,
    val bmiIndex: Float

)
class InvalidParametersException(message: String): Exception(message)