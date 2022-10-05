package com.example.test_slots.di

import com.example.test_slots.data.MainRepositoryImpl
import com.example.test_slots.domain.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindMainRepository(repositoryImpl: MainRepositoryImpl): MainRepository

}