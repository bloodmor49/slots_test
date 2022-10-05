package com.example.test_slots.domain.usecases

import com.example.test_slots.domain.MainRepository
import com.example.test_slots.domain.entities.Coin
import javax.inject.Inject

class InitializeCoinsUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    suspend operator fun invoke() = repository.initializeCoins()
}