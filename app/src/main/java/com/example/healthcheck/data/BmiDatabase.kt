package com.example.healthcheck.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.healthcheck.data.models.BmiMeasurement

@Database(entities = [BmiMeasurement::class], version = 1, exportSchema = false)
abstract class BmiDatabase: RoomDatabase(){
    abstract fun bmiDao(): BmiDao
}
