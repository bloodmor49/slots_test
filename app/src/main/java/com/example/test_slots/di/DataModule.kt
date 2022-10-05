package com.example.test_slots.di

import android.app.Application
import com.example.test_slots.data.database.AppDatabase
import com.example.test_slots.data.database.CoinDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    companion object {

        @Singleton
        @Provides
        fun provideCoinDbDao(application: Application): CoinDbDao {
            return AppDatabase.getInstance(application).coinDbDao()
        }

    }
}