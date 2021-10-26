package com.example.quizapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.data.SharedViewModel
import com.example.quizapp.databinding.FragmentQuizEndBinding

class QuizEndFragment : Fragment() {

    private lateinit var binding : FragmentQuizEndBinding
    private val viewModel : SharedViewModel by activityViewModels()
    private lateinit var resultPoints : TextView
    private lateinit var tryAgainButton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizEndBinding.inflate(inflater, container, false)
        val fragment: View =  binding.root

        initializeElements()
        resultPoints.text = viewModel.getNrOfCorrectAnswers().toString()  + "/" + viewModel.getNrOfQuestions().toString()
        setlisteners()

        return fragment
    }

    private fun initializeElements(){
        resultPoints = binding.tvQuizResultPoints
        tryAgainButton = binding.TryAgainButton
    }

    private fun setlisteners(){
        tryAgainButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_quizEndFragment_to_quizStartFragment)
        }
    }
}