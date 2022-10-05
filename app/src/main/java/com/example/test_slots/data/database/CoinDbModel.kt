package com.example.test_slots.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CoinDbDao.TABLE_NAME)
data class CoinDbModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val number: Int,
)

