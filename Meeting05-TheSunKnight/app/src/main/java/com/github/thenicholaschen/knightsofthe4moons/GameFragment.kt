package com.github.thenicholaschen.knightsofthe4moons


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.thenicholaschen.knightsofthe4moons.databinding.FragmentGameBinding
import com.github.thenicholaschen.knightsofthe4moons.MyApplication.Scene

class GameFragment : Fragment() {

    // Variables
    private val scenes = MyApplication.scenes
    lateinit var currentScene: Scene
    private var selectedActionId: Int = 0
    private var kayaRelationship = 0
    private var leonRelationship = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false)

        // This is IMPORTANT! without this, "@{}" things in [fragment_game.sml] won't work.
        currentScene = scenes[0]

        // Bind this fragment class to the layout
        binding.scene = this

        // Listener for action button
        binding.actionButton.setOnClickListener {

            if(binding.actions.checkedRadioButtonId != -1) {

                when (binding.actions.checkedRadioButtonId) {
                    binding.actionOne.id -> selectedActionId = 0
                    binding.actionTwo.id -> selectedActionId = 1
                    binding.actionThree.id -> selectedActionId = 2
                    binding.actionFour.id -> selectedActionId = 3
                }

                when (currentScene) {
                    scenes[0] -> currentScene = scenes[1] // Intro
                    scenes[1] -> { // Unusual Encounter
                        when(selectedActionId) {
                            0 -> currentScene = scenes[2]
                            1 -> currentScene = scenes[3]
                            2 -> currentScene = scenes[10]
                            3 -> currentScene = scenes[5]
                        }
                    }
                    scenes[2] -> { // Hel-lo th-there...
                        leonRelationship++
                        when(selectedActionId) {
                            0 -> currentScene = scenes[6]
                            1 -> currentScene = scenes[7]
                        }
                    }
                    scenes[3] -> { // Oh no...
                        leonRelationship--
                        when(selectedActionId) {
                            0 -> currentScene = scenes[10]
                            1 -> currentScene = scenes[11]
                        }
                    }
                    scenes[4] -> { // The Heat Gets Worse
                        when(selectedActionId) {
                            0 -> currentScene = scenes[12]
                            1 -> currentScene = scenes[14]
                        }
                    }
                    scenes[5] -> { // Feels Good To Be Generous
                        leonRelationship++
                        kayaRelationship++
                        when(selectedActionId) {
                            0 -> currentScene = scenes[7]
                        }
                    }
                    scenes[6] -> { // Riot
                        kayaRelationship++
                        leonRelationship--
                        when(selectedActionId) {
                            0 -> currentScene = scenes[8]
                        }
                    }
                    scenes[7] -> { // badEnding1
                        MyApplication.badEnding1 = true
                        ending()
                    }
                    scenes[8] -> { //
                        when(selectedActionId) {
                            0 -> currentScene = scenes[9]
                            1 -> currentScene = scenes[7]
                            2 -> currentScene = scenes[13]
                        }
                    }
                    scenes[9] -> {
                        when(selectedActionId) {
                            0 -> currentScene = scenes[7]
                            1 -> currentScene = scenes[10]
                            2 -> currentScene = scenes[12]
                        }
                    }
                    scenes[10] -> {
                        when(selectedActionId) {
                            0 -> currentScene = scenes[13]
                            1 -> currentScene = scenes[14]
                        }
                    }
                    scenes[11] -> {
                        when(selectedActionId) {
                            0 -> currentScene = scenes[5]
                            1 -> currentScene = scenes[6]
                        }
                    }
                    scenes[12] -> {
                        when(selectedActionId) {
                            0 -> currentScene = scenes[15] //bad ending2
                            1 -> currentScene = scenes[17] //good
                        }
                    }
                    scenes[13] -> {
                        when(selectedActionId) {
                            0 -> currentScene = scenes[15] //bad ending2
                            1 -> currentScene = scenes[16] //normal ending
                            2 -> currentScene = scenes[15] //bad ending2
                        }
                    }
                    scenes[14] -> { //badEnding 2
                        MyApplication.badEnding2 = true
                        ending()
                    }
                    scenes[15] -> { //badEnding 3
                        MyApplication.badEnding3 = true
                        ending()
                    }
                    scenes[16] -> { //normalEnding
                        MyApplication.normalEnding = true
                        ending()
                    }
                    scenes[17] -> {
                        when(selectedActionId) {
                            0 -> currentScene = scenes[18]
                        }
                    }
                    scenes[18] -> {
                        when(selectedActionId) {
                            0 -> currentScene = scenes[19]
                            1 -> currentScene = scenes[20]
                        }
                    }
                    scenes[19] -> {
                        MyApplication.badEnding4 = true
                        ending()
                    }
                    scenes[20] -> {
                        MyApplication.bestEnding = true
                        ending()
                    }
                }

                // Disabling options if choice == "" and enabling them if not
                if (currentScene.actions[0] == "") binding.actionOne.isEnabled = false else binding.actionOne.isEnabled = true
                if (currentScene.actions[1] == "") binding.actionTwo.isEnabled = false else binding.actionTwo.isEnabled = true
                if (currentScene.actions[2] == "") binding.actionThree.isEnabled = false else binding.actionThree.isEnabled = true
                if (currentScene.actions[3] == "") binding.actionFour.isEnabled = false else binding.actionFour.isEnabled = true

                binding.actions.clearCheck()
                binding.scrollView.fullScroll(ScrollView.FOCUS_UP)
                binding.invalidateAll()
            } else {
                Toast.makeText(this.activity, "Select one of the actions to continue!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun ending() {
        kayaRelationship = 0
        leonRelationship = 0
        when(selectedActionId) {
            0 -> this.activity!!.onBackPressed()
            1 -> currentScene = scenes[0]
        }
    }

}
