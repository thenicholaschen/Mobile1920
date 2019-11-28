package com.github.thenicholaschen.financialtracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Balance::class, Expense::class], version = 9, exportSchema = false)
abstract class BalanceDatabase : RoomDatabase() {

    abstract fun balanceDao(): BalanceDao
    abstract fun expenseDao(): ExpenseDao


    companion object {
        private var instance: BalanceDatabase? = null

        fun getInstance(context: Context): BalanceDatabase? {
            if (instance == null) {
                synchronized(BalanceDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BalanceDatabase::class.java, "balance_database"
                    )
                        .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                        .build()
                }
            }
            return instance
        }

    }

}