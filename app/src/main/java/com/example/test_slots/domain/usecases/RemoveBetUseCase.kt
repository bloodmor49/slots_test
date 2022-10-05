package com.example.test_slots.domain.usecases

import com.example.test_slots.domain.MainRepository
import com.example.test_slots.domain.entities.Coin
import javax.inject.Inject

class RemoveBetUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    operator fun invoke(currentBet:Int) = repository.removeBet(currentBet)
}