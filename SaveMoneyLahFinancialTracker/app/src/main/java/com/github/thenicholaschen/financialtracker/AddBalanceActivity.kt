package com.github.thenicholaschen.financialtracker

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_balance.*
import kotlinx.android.synthetic.main.activity_add_expense.*
import java.util.*

class AddBalanceActivity : AppCompatActivity() {

    private val cal = Calendar.getInstance()

    companion object {
        const val EXTRA_ID_BALANCE = "com.github.thenicholaschen.todolist.EXTRA_ID_BALANCE"
        const val EXTRA_AMOUNT_BALANCE = "com.github.thenicholaschen.todolist.EXTRA_AMOUNT_BALANCE"
        const val EXTRA_DESCRIPTION_BALANCE = "com.github.thenicholaschen.todolist.EXTRA_DESCRIPTION_BALANCE"
        const val EXTRA_DATE_BALANCE = "com.github.thenicholaschen.todolist.EXTRA_DATE_BALANCE"
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_balance)

        if (intent.hasExtra(EXTRA_ID_BALANCE)) {
            title = "Edit Balance"
            edit_text_balance.setText(intent.getStringExtra(EXTRA_AMOUNT_BALANCE))
            edit_text_description_balance.setText(intent.getStringExtra(EXTRA_DESCRIPTION_BALANCE))
            edit_text_date_balance.text = intent.getStringExtra(EXTRA_DATE_BALANCE)
        } else {
            title = "Add Balance"
        }


        edit_text_date_balance.setOnClickListener {
            val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, day)

                edit_text_date_balance.text = SimpleDateFormat("dd MMM yyyy").format(cal.time)
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(
                Calendar.DAY_OF_MONTH)).show()
        }

        savebtnBalance.setOnClickListener {
            saveBalance()
        }

        cancelbtnBalance.setOnClickListener {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
    }

    private fun saveBalance() {
        if (edit_text_balance.text.toString().trim().isBlank() || edit_text_description_balance.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_AMOUNT_BALANCE, edit_text_balance.text.toString().toDouble())
            putExtra(EXTRA_DESCRIPTION_BALANCE, edit_text_description_balance.text.toString())
            putExtra(EXTRA_DATE_BALANCE, edit_text_date_balance.text.toString())
            if (intent.getIntExtra(EXTRA_ID_BALANCE, -1) != -1) {
                putExtra(EXTRA_ID_BALANCE, intent.getIntExtra(EXTRA_ID_BALANCE, -1))
            }
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
