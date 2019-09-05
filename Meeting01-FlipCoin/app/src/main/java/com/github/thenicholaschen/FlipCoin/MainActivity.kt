package com.github.thenicholaschen.FlipCoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.Image
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var headsOrTails: ImageView
    lateinit var flipInfo: TextView
    lateinit var flipButton: Button
    lateinit var appTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appTitle = findViewById(R.id.title) as TextView
        headsOrTails = findViewById(R.id.coin) as ImageView
        flipInfo = findViewById(R.id.head_or_tail) as TextView
        flipButton = findViewById(R.id.flip) as Button

        flipButton.setOnClickListener {
            flipCoin()
            Toast.makeText(this, "You have flipped this coin!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun flipCoin() {

        appTitle.text = " "
        val randomInt = Random().nextInt(2) + 1
        if (randomInt == 1) {
            flipInfo.text = "You have flipped heads!"
            headsOrTails.setImageResource(R.drawable.heads)
        } else {
            flipInfo.text = "You have flipped tails!"
            headsOrTails.setImageResource(R.drawable.tails)
        }
    }
}
