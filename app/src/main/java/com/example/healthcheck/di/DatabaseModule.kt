package com.example.healthcheck.di

import android.content.Context
import androidx.room.Room
import com.example.healthcheck.data.BmiDatabase
import com.example.healthcheck.util.Constants.BMI_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        BmiDatabase::class.java,
        BMI_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: BmiDatabase) = database.bmiDao()
}