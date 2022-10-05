package com.example.test_slots.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoinDbModel::class],
    version = 3,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coinDbDao(): CoinDbDao

    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "spin_db.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }
}