package com.github.thenicholaschen.financialtracker

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.thenicholaschen.financialtracker.data.Expense
import com.github.thenicholaschen.financialtracker.adapters.todoAdapter
import com.github.thenicholaschen.financialtracker.adapters.todoAdapterBalance
import com.github.thenicholaschen.financialtracker.data.Balance
import com.github.thenicholaschen.financialtracker.viewmodels.BalanceViewModel
import com.github.thenicholaschen.financialtracker.viewmodels.ExpenseViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.absoluteValue


class MainActivity : AppCompatActivity() {

    companion object {
        const val ADD_EXPENSE_REQUEST = 1
        const val EDIT_EXPENSE_REQUEST = 2
        const val ADD_BALANCE_REQUEST = 3
        const val EDIT_BALANCE_REQUEST = 4
    }

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var balanceViewModel: BalanceViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var finalAmount: TextView = findViewById(R.id.text_view_finalAmount)

        var sumBalance: TextView = findViewById(R.id.text_view_balance)
        var sumExpense: TextView = findViewById(R.id.text_view_expense)

        var _finalAmount = finalAmount.text.toString().toInt()

        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel::class.java)
        balanceViewModel = ViewModelProviders.of(this).get(BalanceViewModel::class.java)

        buttonAddExpense.setOnClickListener {
            startActivityForResult(
                Intent(this, AddExpenseActivity::class.java),
                ADD_EXPENSE_REQUEST
            )
            expenseViewModel.getSumExpense().observe(this, Observer<Int> { _sumExpense ->
                sumExpense.text = _sumExpense.toString()
                _finalAmount-=_sumExpense.toInt()
                finalAmount.text = _finalAmount.toString()
            })
        }

        reset_final_balance.setOnClickListener {
            finalAmount.text = "0"
        }

        buttonAddBalance.setOnClickListener {
            startActivityForResult(
                Intent(this, AddBalanceActivity::class.java),
                ADD_BALANCE_REQUEST
            )
            balanceViewModel.getSumBalance().observe(this, Observer<Int> { _sumBalance ->
            sumBalance.text = _sumBalance.toString()
            _finalAmount+=_sumBalance.toInt()
            finalAmount.text = _finalAmount.toString()
        })
        }

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        recycler_view_balance.layoutManager = LinearLayoutManager(this)
        recycler_view_balance.setHasFixedSize(true)

        val adapter = todoAdapter()
        val adapterBalance = todoAdapterBalance()

        recycler_view.adapter = adapter
        recycler_view_balance.adapter = adapterBalance

        expenseViewModel.getAllExpense().observe(this, Observer<List<Expense>> {
            adapter.submitList(it)
        })

        balanceViewModel.getAllBalance().observe(this, Observer<List<Balance>> {
            adapterBalance.submitList(it)
        })





        fun deleteExpense() {
            expenseViewModel.getSumExpense().observe(this, Observer<Int> { _sumExpense ->
                _finalAmount+=_sumExpense.toInt()
                finalAmount.text = _finalAmount.toString()
            })
        }

        fun deleteBalance() {
            balanceViewModel.getSumBalance().observe(this, Observer<Int> { _sumExpense ->
                _finalAmount-=_sumExpense.toInt()
                finalAmount.text = _finalAmount.toString()
            })
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                expenseViewModel.delete(adapter.getExpenseAt(viewHolder.adapterPosition))
                deleteExpense()
                Toast.makeText(baseContext, "Expense Deleted!", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(recycler_view)

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                balanceViewModel.delete(adapterBalance.getBalanceAt(viewHolder.adapterPosition))
                deleteBalance()
                Toast.makeText(baseContext, "Balance Deleted!", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(recycler_view_balance)

        adapter.setOnItemClickListener(object : todoAdapter.OnItemClickListener {
            override fun onItemClick(expense: Expense) {
                val intent = Intent(baseContext, AddExpenseActivity::class.java)
                intent.putExtra(AddExpenseActivity.EXTRA_ID, expense.id)
                intent.putExtra(AddExpenseActivity.EXTRA_AMOUNT, expense.amount.toString().toInt())
                intent.putExtra(AddExpenseActivity.EXTRA_DESCRIPTION, expense.description)
                intent.putExtra(AddExpenseActivity.EXTRA_DATE, expense.date)

                startActivityForResult(intent, EDIT_EXPENSE_REQUEST)
            }
        })

        adapterBalance.setOnItemClickListener(object : todoAdapterBalance.OnItemClickListener {
            override fun onItemClick(balance: Balance) {
                val intent = Intent(baseContext, AddBalanceActivity::class.java)
                intent.putExtra(AddBalanceActivity.EXTRA_ID_BALANCE, balance.id)
                intent.putExtra(AddBalanceActivity.EXTRA_AMOUNT_BALANCE, balance.amount.toString().toInt())
                intent.putExtra(AddBalanceActivity.EXTRA_DESCRIPTION_BALANCE, balance.description)
                intent.putExtra(AddBalanceActivity.EXTRA_DATE_BALANCE, balance.date)

                startActivityForResult(intent, EDIT_BALANCE_REQUEST)
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_EXPENSE_REQUEST && resultCode == Activity.RESULT_OK) {
            val newExpense = Expense(
                data!!.getIntExtra(AddExpenseActivity.EXTRA_AMOUNT, 0),
                data.getStringExtra(AddExpenseActivity.EXTRA_DESCRIPTION),
                data.getStringExtra(AddExpenseActivity.EXTRA_DATE)
            )

            expenseViewModel.insert(newExpense)

            Toast.makeText(this, "Expense saved!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_EXPENSE_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(AddExpenseActivity.EXTRA_ID, -1)

            if (id == -1) {
                Toast.makeText(this, "Could not update! Error!", Toast.LENGTH_SHORT).show()
            }

            val updateExpense = Expense(
                data!!.getIntExtra(AddExpenseActivity.EXTRA_AMOUNT, 0),
                data.getStringExtra(AddExpenseActivity.EXTRA_DESCRIPTION),
                data.getStringExtra(AddExpenseActivity.EXTRA_DATE)
            )
            updateExpense.id = data.getIntExtra(AddExpenseActivity.EXTRA_ID, -1)
            expenseViewModel.update(updateExpense)

        } else if (requestCode == ADD_BALANCE_REQUEST && resultCode == Activity.RESULT_OK) {
            val newBalance = Balance(
                data!!.getIntExtra(AddBalanceActivity.EXTRA_AMOUNT_BALANCE, 0),
                data.getStringExtra(AddBalanceActivity.EXTRA_DESCRIPTION_BALANCE),
                data.getStringExtra(AddBalanceActivity.EXTRA_DATE_BALANCE)
            )
            balanceViewModel.insert(newBalance)

            Toast.makeText(this, "Balance saved!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_BALANCE_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(AddBalanceActivity.EXTRA_ID_BALANCE, -1)

            if (id == -1) {
                Toast.makeText(this, "Could not update! Error!", Toast.LENGTH_SHORT).show()
            }

            val updateBalance = Balance(
                data!!.getIntExtra(AddBalanceActivity.EXTRA_AMOUNT_BALANCE, 0),
                data.getStringExtra(AddBalanceActivity.EXTRA_DESCRIPTION_BALANCE),
                data.getStringExtra(AddBalanceActivity.EXTRA_DATE_BALANCE)
            )
            updateBalance.id = data.getIntExtra(AddBalanceActivity.EXTRA_ID_BALANCE, -1)
            balanceViewModel.update(updateBalance)
        } else {
            Toast.makeText(this, "Data not Saved!", Toast.LENGTH_SHORT).show()
        }
    }
}


