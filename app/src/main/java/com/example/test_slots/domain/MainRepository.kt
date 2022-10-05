package com.example.test_slots.domain

import androidx.lifecycle.LiveData
import com.example.test_slots.domain.entities.Coin

interface MainRepository {
    suspend fun getCoins(): Coin
    suspend fun insertCoins(item: Coin)
    suspend fun initializeCoins()

    fun addBet(currentBet:Int, coinsNum: Int):Int
    fun removeBet(currentBet:Int):Int
    suspend fun spin(betValue:Int, results: List<List<Int>>)
}