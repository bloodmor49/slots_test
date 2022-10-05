package com.example.test_slots.domain.usecases

import com.example.test_slots.domain.MainRepository
import com.example.test_slots.domain.entities.Coin
import javax.inject.Inject

class SpinUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    suspend operator fun invoke(betValue: Int, results: List<List<Int>>) =
        repository.spin(betValue, results)
}