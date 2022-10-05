package com.example.test_slots.domain.entities


data class Coin(
    val id: Int = COIN_ID,
    val number: Int = START_COIN_VALUE,
) {
    companion object {
        const val START_COIN_VALUE = 1000
        const val COIN_ID = 1
    }
}