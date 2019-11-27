package com.github.thenicholaschen.financialtracker

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.allyants.notifyme.NotifyMe
import com.github.thenicholaschen.financialtracker.notification.AlarmReceiver
import kotlinx.android.synthetic.main.activity_add_balance.*
import kotlinx.android.synthetic.main.activity_add_expense.*
import org.w3c.dom.Text
import java.util.*


class AddExpenseActivity : AppCompatActivity() {

    private val cal = Calendar.getInstance()

    companion object {
        const val EXTRA_ID = "com.github.thenicholaschen.todolist.EXTRA_ID"
        const val EXTRA_AMOUNT = "com.github.thenicholaschen.todolist.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.github.thenicholaschen.todolist.EXTRA_DESCRIPTION"
        const val EXTRA_DATE = "com.github.thenicholaschen.todolist.EXTRA_DATE"
        const val EXTRA_WEEKS = "com.github.thenicholaschen.tofolist.EXTRA_WEEKS"
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        number_picker_weeks.minValue = 1
        number_picker_weeks.maxValue = 27

        var fixedBillLayout : LinearLayout = findViewById(R.id.fixed_bill_layout)
        var fixedBillButtonYes: RadioButton = findViewById(R.id.radio_button_fixedbillyes)
        var fixedBillButtonNo: RadioButton = findViewById(R.id.radio_button_fixedbillno)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Expense"
            edit_text_expense.setText(intent.getStringExtra(EXTRA_AMOUNT))
            edit_text_description.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            edit_text_date.text = intent.getStringExtra(EXTRA_DATE)
            if (number_picker_weeks.value != null) {
                number_picker_weeks.value = intent.getIntExtra(EXTRA_WEEKS, 1)
            }
        } else {
            title = "Add Expense"
        }

        fixedBillButtonYes.setOnClickListener {
            fixedBillLayout.visibility = VISIBLE
        }

        fixedBillButtonNo.setOnClickListener {
            fixedBillLayout.visibility = GONE
        }

        edit_text_duetime.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                cal.set(Calendar.SECOND, 0)

                edit_text_duetime.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        edit_text_date.setOnClickListener {
            val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, day)

                edit_text_date.text = SimpleDateFormat("dd MMM yyyy").format(cal.time)
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        savebtn.setOnClickListener {
            saveExpense()
        }

        cancelbtn.setOnClickListener {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
    }

    private fun saveExpense() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (edit_text_expense.text.toString().trim().isBlank() || edit_text_description.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_AMOUNT, edit_text_expense.text.toString().toInt())
            putExtra(EXTRA_DESCRIPTION, edit_text_description.text.toString())
            putExtra(EXTRA_DATE, edit_text_date.text.toString())
            if (number_picker_weeks.value != null) {
                number_picker_weeks.value = intent.getIntExtra(EXTRA_WEEKS, 1)
            }
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }

        val notificationIntent = Intent(this, AlarmReceiver::class.java)
        notificationIntent.putExtras(data)
        val broadcast = PendingIntent.getBroadcast(
            this,
            100,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.timeInMillis, broadcast)
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
