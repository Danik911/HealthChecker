package com.example.healthcheck.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.healthcheck.util.Constants.BMI_TABLE

@Entity(tableName = BMI_TABLE)
data class BmiResult(
    @PrimaryKey(autoGenerate = true)
    val BmiId: Int = 0,
    val bmiTitle: String,
    val bmiDescription: String,
    val massResult: MassResult,
    val bmiIndex: Double,
    val timestamp: Long

)
class InvalidParametersException(message: String): Exception(message)