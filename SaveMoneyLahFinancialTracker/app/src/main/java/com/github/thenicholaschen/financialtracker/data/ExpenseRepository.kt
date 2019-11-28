package com.github.thenicholaschen.financialtracker.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ExpenseRepository(application: Application) {

    private var expenseDao: ExpenseDao

    private var allExpense: LiveData<List<Expense>>

    private var sumExpense: LiveData<Double>


    init {
        val database: ExpenseDatabase = ExpenseDatabase.getInstance(
            application.applicationContext
        )!!
        expenseDao = database.expenseDao()
        allExpense = expenseDao.getAllExpense()
        sumExpense = expenseDao.getSumExpense()

    }

    fun insert(expense: Expense) {
        InsertDoesAsyncTask(expenseDao).execute(expense)
    }

    fun update(expense: Expense) {
        UpdateDoesAsyncTask(expenseDao).execute(expense)
    }


    fun delete(expense: Expense) {
        DeleteDoesAsyncTask(expenseDao).execute(expense)
    }

    fun getAllExpense(): LiveData<List<Expense>> {
        return allExpense
    }

    fun getSumExpense(): LiveData<Double> {
        return sumExpense
    }

    companion object {
        private class InsertDoesAsyncTask(val expenseDao: ExpenseDao) : AsyncTask<Expense, Unit, Unit>() {

            override fun doInBackground(vararg p0: Expense?) {
                expenseDao.insert(p0[0]!!)
            }
        }

        private class UpdateDoesAsyncTask(val expenseDao: ExpenseDao) : AsyncTask<Expense, Unit, Unit>() {

            override fun doInBackground(vararg p0: Expense?) {
                expenseDao.update(p0[0]!!)
            }
        }

        private class DeleteDoesAsyncTask(val expenseDao: ExpenseDao) : AsyncTask<Expense, Unit, Unit>() {

            override fun doInBackground(vararg p0: Expense?) {
                expenseDao.delete(p0[0]!!)
            }
        }
    }
}