package com.github.thenicholaschen.financialtracker.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface BalanceDao {

    @Insert
    fun insert(balance: Balance)

    @Update
    fun update(balance: Balance)

    @Delete
    fun delete(balance: Balance)

    @Query("SELECT * FROM balance_table")
    fun getAllBalance(): LiveData<List<Balance>>

    @Query("SELECT COALESCE(SUM(amount),0) FROM balance_table")
    fun getSumBalance(): LiveData<Int>

    @Query("SELECT SUM(balance_table.amount) - SUM(expense_table.amount) FROM balance_table JOIN expense_table")
    fun getFinalBalance(): LiveData<Int>
}