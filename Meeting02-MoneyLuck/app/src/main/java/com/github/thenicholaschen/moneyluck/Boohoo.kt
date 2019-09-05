package com.github.thenicholaschen.moneyluck

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class Boohoo : AppCompatActivity() {

    val TAG="Boohoo"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boohoo_main)
        Log.d(TAG, "Under onCreate")
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