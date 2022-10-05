package com.example.test_slots.data

import com.example.test_slots.data.database.CoinDbModel
import com.example.test_slots.domain.entities.Coin

fun CoinDbModel.toCoin() =  Coin(id, number)

fun Coin.toCoinDbModel() =  CoinDbModel(id = id, number = number)