package com.github.thenicholaschen.financialtracker.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BalanceRepository(application: Application) {

    private var balanceDao: BalanceDao

    private var allBalance: LiveData<List<Balance>>

    private var sumBalance: LiveData<Int>

    private var finalBalance: LiveData<Int>


    init {
        val database: BalanceDatabase = BalanceDatabase.getInstance(
            application.applicationContext
        )!!
        balanceDao = database.balanceDao()
        allBalance = balanceDao.getAllBalance()
        sumBalance = balanceDao.getSumBalance()
        finalBalance = balanceDao.getFinalBalance()
    }

    fun insert(balance: Balance) {
        InsertDoesAsyncTask(balanceDao).execute(balance)
    }

    fun update(balance: Balance) {
        UpdateDoesAsyncTask(balanceDao).execute(balance)
    }


    fun delete(balance: Balance) {
        DeleteDoesAsyncTask(balanceDao).execute(balance)
    }

    fun getAllBalance(): LiveData<List<Balance>> {
        return allBalance
    }

    fun getSumBalance(): LiveData<Int> {
        return sumBalance
    }

    fun getFinalBalance(): LiveData<Int> {
        return finalBalance
    }

    companion object {
        private class InsertDoesAsyncTask(val balanceDao: BalanceDao) : AsyncTask<Balance, Unit, Unit>() {

            override fun doInBackground(vararg p0: Balance?) {
                balanceDao.insert(p0[0]!!)
            }
        }

        private class UpdateDoesAsyncTask(val balanceDao: BalanceDao) : AsyncTask<Balance, Unit, Unit>() {

            override fun doInBackground(vararg p0: Balance?) {
                balanceDao.update(p0[0]!!)
            }
        }

        private class DeleteDoesAsyncTask(val balanceDao: BalanceDao) : AsyncTask<Balance, Unit, Unit>() {

            override fun doInBackground(vararg p0: Balance?) {
                balanceDao.delete(p0[0]!!)
            }
        }
    }
}