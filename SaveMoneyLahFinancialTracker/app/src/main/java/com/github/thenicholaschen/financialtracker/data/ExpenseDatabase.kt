package com.github.thenicholaschen.financialtracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 9, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao


    companion object {
        private var instance: ExpenseDatabase? = null

        fun getInstance(context: Context): ExpenseDatabase? {
            if (instance == null) {
                synchronized(ExpenseDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExpenseDatabase::class.java, "expense_database"
                    )
                        .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                        .build()
                }
            }
            return instance
        }

    }

}