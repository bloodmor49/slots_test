package com.example.test_slots.domain.usecases

import com.example.test_slots.domain.MainRepository
import javax.inject.Inject

class AddBetUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    operator fun invoke(currentBet: Int, coinsNumber: Int) = repository.addBet(currentBet,coinsNumber)
}