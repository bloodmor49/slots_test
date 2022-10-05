package com.example.test_slots.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDbDao {

    companion object {
        const val TABLE_NAME = "coins_list"
    }

    @Query("SELECT * FROM $TABLE_NAME WHERE id =:itemId")
    fun getCoins(itemId: Int): CoinDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoins(dbModel: CoinDbModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun initializeCoins(dbModel: CoinDbModel)
}