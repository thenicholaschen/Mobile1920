package com.github.thenicholaschen.financialtracker.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.github.thenicholaschen.financialtracker.data.ExpenseRepository
import com.github.thenicholaschen.financialtracker.data.Expense

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: ExpenseRepository =
        ExpenseRepository(application)
    private var allExpense: LiveData<List<Expense>> = repository.getAllExpense()
    private var sumExpense: LiveData<Int> = repository.getSumExpense()


    fun insert(expense: Expense) {
        repository.insert(expense)
    }

    fun update(expense: Expense) {
        repository.update(expense)
    }

    fun delete(expense: Expense) {
        repository.delete(expense)
    }

    fun getAllExpense(): LiveData<List<Expense>> {
        return allExpense
    }


    fun getSumExpense(): LiveData<Int> {
        return sumExpense
    }

}