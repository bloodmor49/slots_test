package com.example.test_slots.data.slots_pack

import javax.inject.Inject

class GameConfigure @Inject constructor() {

    fun addBet(currentBet: Int, coins: Int): Int {
        return if (currentBet < coins) currentBet + POINTS_QUALIFIER
        else coins
    }

    fun removeBet(currentBet: Int): Int {
        return if (currentBet > ZERO_POINTS) currentBet - POINTS_QUALIFIER
        else ZERO_POINTS
    }

    fun nullifyBet(): Int = ZERO_POINTS

    fun calculateResultValue(betValue: Int, results: List<List<Int>>): Int {
        val line1 =
            listOf(results[0][0], results[1][0], results[2][0], results[3][0], results[4][0])
        val line2 =
            listOf(results[0][1], results[1][1], results[2][1], results[3][1], results[4][1])
        val line3 =
            listOf(results[0][2], results[1][2], results[2][2], results[3][2], results[4][2])

        val result1 = getRepeats(line1).values
        val result2 = getRepeats(line2).values
        val result3 = getRepeats(line3).values

        val mapOfMultipliers = mutableMapOf(2 to 0, 3 to 0, 4 to 0, 5 to 0)

        for (i in result1) {
            mapOfMultipliers[i] = mapOfMultipliers[i]!! + 1
        }
        for (i in result2) {
            mapOfMultipliers[i] = mapOfMultipliers[i]!! + 1
        }
        for (i in result3) {
            mapOfMultipliers[i] = mapOfMultipliers[i]!! + 1
        }

        return betValue * (mapOfMultipliers[2]!! * 2 +
                        mapOfMultipliers[3]!! * 4 +
                        mapOfMultipliers[4]!! * 6 +
                        mapOfMultipliers[5]!! * 10)
    }

    private fun getRepeats(list: List<Int>): Map<Int, Int> {
        return list.groupingBy { it }.eachCount().filter { it.value > 1 }
    }

    companion object {
        const val ZERO_POINTS = 0
        const val POINTS_QUALIFIER = 100
    }
}