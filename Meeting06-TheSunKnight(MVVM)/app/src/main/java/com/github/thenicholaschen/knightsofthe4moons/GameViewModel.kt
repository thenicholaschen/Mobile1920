package com.github.thenicholaschen.knightsofthe4moons

import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.thenicholaschen.knightsofthe4moons.MyApplication.Companion.scenes

class GameViewModel : ViewModel() {

    private var _scene = MutableLiveData<MyApplication.Scene>()
    val scene: LiveData<MyApplication.Scene>
        get() = _scene

    private var _goToMainMenu = MutableLiveData<Boolean>()
    val goToMainMenu: LiveData<Boolean>
        get() = _goToMainMenu

    val scenes = MyApplication.scenes
    var leonRelationship = 0
    var kayaRelationship = 0
    var selectedActionId = -1

    init {
        _scene.value = scenes[0]
        _goToMainMenu.value = false
    }

    fun onAction(actions: RadioGroup, opt1: RadioButton, opt2: RadioButton, opt3: RadioButton, opt4: RadioButton, scrollView: ScrollView) {
        val checkedRadioButtonId = actions.checkedRadioButtonId

        when(checkedRadioButtonId) {
            opt1.id -> selectedActionId = 0
            opt2.id -> selectedActionId = 1
            opt3.id -> selectedActionId = 2
            opt4.id -> selectedActionId = 3
        }

        if(checkedRadioButtonId != -1) {
            when (checkedRadioButtonId) {
                opt1.id -> selectedActionId = 0
                opt2.id -> selectedActionId = 1
                opt3.id -> selectedActionId = 2
                opt4.id -> selectedActionId = 3
            }

            when (_scene.value) {
                scenes[0] -> _scene.value = scenes[1] // Intro
                scenes[1] -> { // Unusual Encounter
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[2]
                        1 -> _scene.value = scenes[3]
                        2 -> _scene.value = scenes[10]
                        3 -> _scene.value = scenes[5]
                    }
                }
                scenes[2] -> { // Hel-lo th-there...
                    leonRelationship++
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[6]
                        1 -> _scene.value = scenes[7]
                    }
                }
                scenes[3] -> { // Oh no...
                    leonRelationship--
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[10]
                        1 -> _scene.value = scenes[11]
                    }
                }
                scenes[4] -> { // The Heat Gets Worse
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[12]
                        1 -> _scene.value = scenes[14]
                    }
                }
                scenes[5] -> { // Feels Good To Be Generous
                    leonRelationship++
                    kayaRelationship++
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[7]
                    }
                }
                scenes[6] -> { // Riot
                    kayaRelationship++
                    leonRelationship--
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[8]
                    }
                }
                scenes[7] -> { // badEnding1
                    MyApplication.badEnding1 = true
                    ending()
                }
                scenes[8] -> { //
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[9]
                        1 -> _scene.value = scenes[7]
                        2 -> _scene.value = scenes[13]
                    }
                }
                scenes[9] -> {
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[7]
                        1 -> _scene.value = scenes[10]
                        2 -> _scene.value = scenes[12]
                    }
                }
                scenes[10] -> {
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[13]
                        1 -> _scene.value = scenes[14]
                    }
                }
                scenes[11] -> {
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[5]
                        1 -> _scene.value = scenes[6]
                    }
                }
                scenes[12] -> {
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[15] //bad ending2
                        1 -> _scene.value = scenes[17] //good
                    }
                }
                scenes[13] -> {
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[15] //bad ending2
                        1 -> _scene.value = scenes[16] //normal ending
                        2 -> _scene.value = scenes[15] //bad ending2
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
                        0 -> _scene.value = scenes[18]
                    }
                }
                scenes[18] -> {
                    when(selectedActionId) {
                        0 -> _scene.value = scenes[19]
                        1 -> _scene.value = scenes[20]
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
            if (_scene.value!!.actions[0] == "") opt1.isEnabled = false else opt1.isEnabled = true
            if (_scene.value!!.actions[1] == "") opt2.isEnabled = false else opt2.isEnabled = true
            if (_scene.value!!.actions[2] == "") opt3.isEnabled = false else opt3.isEnabled = true
            if (_scene.value!!.actions[3] == "") opt4.isEnabled = false else opt4.isEnabled = true

            actions.clearCheck()
            scrollView.fullScroll(ScrollView.FOCUS_UP)
    }

}

private fun ending() {
    kayaRelationship = 0
    leonRelationship = 0
    when (selectedActionId) {
        0 -> _goToMainMenu.value = true
        1 -> _scene.value = scenes[0]
    }
}
}