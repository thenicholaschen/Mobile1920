package com.github.thenicholaschen.financialtracker.notification

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.thenicholaschen.financialtracker.AddExpenseActivity
import com.github.thenicholaschen.financialtracker.MainActivity
import com.github.thenicholaschen.financialtracker.R
import kotlinx.android.synthetic.main.activity_add_expense.*

import kotlinx.android.synthetic.main.activity_notification.*
import java.util.*
import kotlin.random.Random

class NotificationActivityPage : AppCompatActivity() {


    private val cal = Calendar.getInstance()

    companion object {
        const val EXTRA_ID = "com.github.thenicholaschen.todolist.EXTRA_ID"
        const val EXTRA_AMOUNT = "com.github.thenicholaschen.todolist.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.github.thenicholaschen.todolist.EXTRA_DESCRIPTION"
        const val EXTRA_DATE = "com.github.thenicholaschen.todolist.EXTRA_DATE"
        const val EXTRA_WEEKS = "com.github.thenicholaschen.tofolist.EXTRA_WEEKS"
    }

    var answer: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val v = this.applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        var notificationPayBtn : Button = findViewById(R.id.kill)

        notificationPayBtn.setOnClickListener {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
    }

}