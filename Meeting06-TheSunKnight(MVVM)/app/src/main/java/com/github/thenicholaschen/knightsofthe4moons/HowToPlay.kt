package com.github.thenicholaschen.knightsofthe4moons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.github.thenicholaschen.knightsofthe4moons.databinding.FragmentHowToPlayBinding

/**
 * A simple [Fragment] subclass.
 */
class HowToPlay : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHowToPlayBinding>(inflater,
            R.layout.fragment_how_to_play, container, false)

        return binding.root
    }


}