package com.example.healthcheck.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.healthcheck.data.models.BmiResult

@Database(entities = [BmiResult::class], version = 1, exportSchema = false)
abstract class BmiDatabase: RoomDatabase(){
    abstract fun bmiDao(): BmiDao
}
