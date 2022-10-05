package com.example.test_slots

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun how_many_repeats() {
        val list1 = listOf(1, 2, 2, 3, 5) //0 повторов
        val list2 = listOf(1, 2, 3, 4, 5) // 2
        val list3 = listOf(1, 2, 3, 4, 5) // 3 + 2
        val list4 = listOf(1, 2, 3, 4, 5) // 4
        val list5 = listOf(1, 2, 3, 4, 5) // 5

        val result1 = list1.groupingBy { it }.eachCount().filter { it.value > 1 }.values
        val result2 = list2.groupingBy { it }.eachCount().filter { it.value > 1 }.values
        val result3 = list3.groupingBy { it }.eachCount().filter { it.value > 1 }.values
        val result4 = list4.groupingBy { it }.eachCount().filter { it.value > 1 }.values
        val result5 = list5.groupingBy { it }.eachCount().filter { it.value > 1 }.values

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
        for (i in result4) {
            mapOfMultipliers[i] = mapOfMultipliers[i]!! + 1
        }
        for (i in result5) {
            mapOfMultipliers[i] = mapOfMultipliers[i]!! + 1
        }
        val bet = 100
        val resultWIn = bet *
                (mapOfMultipliers[2]!! * 2 +
                mapOfMultipliers[3]!! * 4 +
                mapOfMultipliers[4]!! * 6 +
                mapOfMultipliers[5]!! * 10)


                println()
                println (mapOfMultipliers)
                println (resultWIn - bet)
                println ()

    }
}