package com.github.thenicholaschen.financialtracker.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.thenicholaschen.financialtracker.data.Balance
import com.github.thenicholaschen.financialtracker.data.BalanceRepository
import com.github.thenicholaschen.financialtracker.data.ExpenseRepository


class BalanceViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: BalanceRepository =
        BalanceRepository(application)
    private var repositoryExp: ExpenseRepository =
        ExpenseRepository(application)
    private var allBalance: LiveData<List<Balance>> = repository.getAllBalance()
    private var sumBalance: LiveData<Double> = repository.getSumBalance()
    private var sumExpense: LiveData<Double> = repositoryExp.getSumExpense()
    var amount = MutableLiveData<Double>()

    init {
        amount.value = 0.0
    }

    fun insert(balance: Balance) {
        repository.insert(balance)
    }

    fun update(balance: Balance) {
        repository.update(balance)
    }

    fun delete(balance: Balance) {
        repository.delete(balance)
    }

    fun getAllBalance(): LiveData<List<Balance>> {
        return allBalance
    }

    fun getSumBalance(): LiveData<Double> {
        return sumBalance
    }

    fun getFinalBalance(): MutableLiveData<Double> {
        amount.value = (sumBalance.value)?.minus(sumExpense.value!!)
        if(amount.value == null) {
            amount.value = (amount.value)?.plus(1)
        }
        return amount
    }
}

