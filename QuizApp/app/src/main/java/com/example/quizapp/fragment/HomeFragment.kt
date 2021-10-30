package com.example.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var testYourSkillsButton: Button
    private lateinit var readQuestionsButton: Button
    private lateinit var createQuestionButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val fragment: View = binding.root
        initializeElements()
        setListeners()
        return fragment
    }

    private fun initializeElements() {
        testYourSkillsButton = binding.testYourSkillsButton
        readQuestionsButton = binding.readQuestionsButton
        createQuestionButton = binding.createQuestionButton
    }

    private fun setListeners() {
        testYourSkillsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizStartFragment)
        }
        readQuestionsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_questionListFragment)
        }
        createQuestionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_questionAddFragment)
        }
    }
}