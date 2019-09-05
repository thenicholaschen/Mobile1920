package com.github.thenicholaschen.moneyluck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG="Main Activity"
    lateinit var money: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "In onCreate")
        //declare animation
        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        //declare what to animate
        val headerTitle = findViewById(R.id.header) as TextView
        val footerTitle = findViewById(R.id.footer) as TextView
        val luckButton = findViewById(R.id.luckButton) as Button
        money = findViewById(R.id.moneyImage) as ImageView

        //set the animation
        headerTitle.startAnimation(ttb)
        footerTitle.startAnimation(ttb)

        luckButton.setOnClickListener {
            randomMoney()
            Toast.makeText(this, "Testing luck!", Toast.LENGTH_SHORT).show()
            }

        }
    //I left off here.
    private fun randomMoney() {

        val randomInt = Random().nextInt(4) + 1
        if (randomInt == 1) {
            money.setImageResource(R.drawable.five)
            Toast.makeText(this, "You got five dollars!", Toast.LENGTH_LONG).show()
        } else if (randomInt == 2) {
            money.setImageResource(R.drawable.ten)
            Toast.makeText(this, "You got ten dollars!", Toast.LENGTH_LONG).show()
        }
        else if (randomInt == 3){
            money.setImageResource(R.drawable.fifty)
            Toast.makeText(this, "You got fifty dollars!", Toast.LENGTH_LONG).show()
        }
        else {
            val i = Intent(this,Boohoo::class.java)
            startActivity(i)
        }

        }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "In onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "In onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "In onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "In onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "In onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "In onRestart")
    }

    }



