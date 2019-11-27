package com.github.thenicholaschen.financialtracker.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface ExpenseDao {

    @Insert
    fun insert(expense: Expense)

    @Update
    fun update(expense: Expense)

    @Delete
    fun delete(expense: Expense)

    @Query("SELECT * FROM expense_table")
    fun getAllExpense(): LiveData<List<Expense>>

    @Query("SELECT COALESCE(SUM(amount),0) from expense_table")
    fun getSumExpense(): LiveData<Int>
}
