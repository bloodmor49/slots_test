package com.example.test_slots.domain.usecases

import com.example.test_slots.domain.MainRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    suspend operator fun invoke() = repository.getCoins()
}