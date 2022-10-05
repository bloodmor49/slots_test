package com.example.test_slots.data

import com.example.test_slots.data.database.CoinDbDao
import com.example.test_slots.data.slots_pack.GameConfigure
import com.example.test_slots.domain.MainRepository
import com.example.test_slots.domain.entities.Coin
import com.example.test_slots.domain.entities.Coin.Companion.COIN_ID
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val dbDao: CoinDbDao,
    private val gameConfigure: GameConfigure,
) : MainRepository {
    override suspend fun getCoins(): Coin {
        return dbDao.getCoins(COIN_ID).toCoin()
    }

    override suspend fun insertCoins(item: Coin) {
        dbDao.insertCoins(item.toCoinDbModel())
    }

    override suspend fun initializeCoins() {
        dbDao.initializeCoins(Coin().toCoinDbModel())
    }

    override fun addBet(currentBet: Int, coinsNum: Int): Int {
        return gameConfigure.addBet(currentBet, coinsNum)
    }

    override fun removeBet(currentBet: Int): Int {
        return gameConfigure.removeBet(currentBet)
    }

    override suspend fun spin(betValue: Int, results: List<List<Int>>) {
        val victoryCoins = gameConfigure.calculateResultValue(betValue, results)
        val currentCoins = dbDao.getCoins(COIN_ID)
        val newCoins = currentCoins.copy(number = currentCoins.number + victoryCoins)
        dbDao.insertCoins(newCoins)
    }
}