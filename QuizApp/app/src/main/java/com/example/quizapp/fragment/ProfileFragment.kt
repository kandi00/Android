package com.example.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.data.SharedViewModel
import com.example.quizapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var playerNameText : TextView
    private lateinit var highestScore : TextView
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val fragment: View =  binding.root
        initializeElements()
        return fragment
    }

    private fun initializeElements(){
        playerNameText = binding.playerNameText
        highestScore = binding.highScoreText

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        playerNameText.text = viewModel.getPlayerName()
        highestScore.text = viewModel.getHighestScore().toString()
    }
}