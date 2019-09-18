package com.github.thenicholaschen.findthestar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.github.thenicholaschen.findthestar.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var selectedView: View
    private var selectedId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val boxesId: List<Int> = listOf(
            binding.box01.id,
            binding.box02.id,
            binding.box03.id,
            binding.box04.id,
            binding.box05.id,
            binding.box06.id,
            binding.box07.id,
            binding.box08.id,
            binding.box09.id,
            binding.box10.id,
            binding.box11.id,
            binding.box12.id,
            binding.box13.id,
            binding.box14.id,
            binding.box15.id,
            binding.box16.id,
            binding.box17.id,
            binding.box18.id,
            binding.box19.id,
            binding.box20.id
        )
        selectedId = boxesId[Random.nextInt(0, 20)]
        Log.d("ActivityMain :: ", "SelectedID: ${selectedId}; From: ${boxesId}")
        setListeners()
    }

    private fun setBehavior(view: View) {
        if(view.id == selectedId) {
            setButtonBackground(view, android.R.drawable.btn_star_big_on)
            selectedView = view
            winHandler()
        } else setButtonBackground(view, android.R.drawable.ic_delete)
    }

    private fun setButtonBackground(view: View, buttonIcon: Int) {
        view.setBackgroundResource(buttonIcon)
    }

    private fun winHandler() {
        Toast.makeText(this, "YOU FOUND THE MISSING YELLOW STAR! Click on it to restart the game!", Toast.LENGTH_LONG).show()
        selectedView.setOnClickListener {
            resetAll()
        }
    }

    private fun resetAll() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val boxesId: List<Int> = listOf(
            binding.box01.id,
            binding.box02.id,
            binding.box03.id,
            binding.box04.id,
            binding.box05.id,
            binding.box06.id,
            binding.box07.id,
            binding.box08.id,
            binding.box09.id,
            binding.box10.id,
            binding.box11.id,
            binding.box12.id,
            binding.box13.id,
            binding.box14.id,
            binding.box15.id,
            binding.box16.id,
            binding.box17.id,
            binding.box18.id,
            binding.box19.id,
            binding.box20.id
        )
        selectedId = boxesId[Random.nextInt(0, 20)]
        Log.d("ActivityMain :: ", "SelectedID: ${selectedId}; From: ${boxesId}")

        binding.apply {
            val box01 = box01
            val box02 = box02
            val box03 = box03
            val box04 = box05
            val box05 = box05
            val box06 = box06
            val box07 = box07
            val box08 = box08
            val box09 = box09
            val box10 = box10
            val box11 = box11
            val box12 = box12
            val box13 = box13
            val box14 = box14
            val box15 = box15
            val box16 = box16
            val box17 = box17
            val box18 = box18
            val box19 = box19
            val box20 = box20


            val clickableViews: List<View> =
                listOf(box01, box02, box03, box04, box05, box06, box07, box08, box09, box10,
                    box11, box12, box13, box14, box15, box16, box17, box18, box19, box20)

            for (item in clickableViews) {
                item.setOnClickListener {
                    setBehavior(it)
                }
                item.setBackgroundColor(Color.WHITE)
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            val box01 = box01
            val box02 = box02
            val box03 = box03
            val box04 = box05
            val box05 = box05
            val box06 = box06
            val box07 = box07
            val box08 = box08
            val box09 = box09
            val box10 = box10
            val box11 = box11
            val box12 = box12
            val box13 = box13
            val box14 = box14
            val box15 = box15
            val box16 = box16
            val box17 = box17
            val box18 = box18
            val box19 = box19
            val box20 = box20

            val clickableViews: List<View> =
                listOf(box01, box02, box03, box04, box05, box06, box07, box08, box09, box10,
                    box11, box12, box13, box14, box15, box16, box17, box18, box19, box20)

            for (item in clickableViews) {
                item.setOnClickListener {
                    setBehavior(it)
                }
            }
        }
    }
}
