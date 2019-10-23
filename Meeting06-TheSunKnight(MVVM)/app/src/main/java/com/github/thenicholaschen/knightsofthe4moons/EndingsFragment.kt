package com.github.thenicholaschen.knightsofthe4moons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.github.thenicholaschen.knightsofthe4moons.databinding.FragmentEndingsBinding

/**
 * A simple [Fragment] subclass.
 */
class EndingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEndingsBinding>(inflater,
            R.layout.fragment_endings, container, false)

        // Ending Buttons Listeners...
        binding.badEndingOneButton.setOnClickListener {
            MyApplication.currentDisplayedEnding = MyApplication.scenes[7]
            it.findNavController().navigate(R.id.action_endingsFragment_to_endingDisplay)
        }
        binding.badEndingTwoButton.setOnClickListener {
            MyApplication.currentDisplayedEnding = MyApplication.scenes[10]
            it.findNavController().navigate(R.id.action_endingsFragment_to_endingDisplay)
        }
        binding.badEndingThreeButton.setOnClickListener {
            MyApplication.currentDisplayedEnding = MyApplication.scenes[29]
            it.findNavController().navigate(R.id.action_endingsFragment_to_endingDisplay)
        }
        binding.badEndingFourButton.setOnClickListener {
            MyApplication.currentDisplayedEnding = MyApplication.scenes[34]
            it.findNavController().navigate(R.id.action_endingsFragment_to_endingDisplay)
        }
        binding.normalEndingButton.setOnClickListener {
            MyApplication.currentDisplayedEnding = MyApplication.scenes[35]
            it.findNavController().navigate(R.id.action_endingsFragment_to_endingDisplay)
        }
        binding.bestEndingButton.setOnClickListener {
            MyApplication.currentDisplayedEnding = MyApplication.scenes[37]
            it.findNavController().navigate(R.id.action_endingsFragment_to_endingDisplay)
        }

        // Enabling/disabling buttons
        if (MyApplication.badEnding1) binding.badEndingOneButton.isEnabled = true
        if (MyApplication.badEnding2) binding.badEndingTwoButton.isEnabled = true
        if (MyApplication.badEnding3) binding.badEndingThreeButton.isEnabled = true
        if (MyApplication.badEnding4) binding.badEndingFourButton.isEnabled = true
        if (MyApplication.normalEnding) binding.normalEndingButton.isEnabled = true
        if (MyApplication.bestEnding) binding.bestEndingButton.isEnabled = true

        return binding.root
    }
}
