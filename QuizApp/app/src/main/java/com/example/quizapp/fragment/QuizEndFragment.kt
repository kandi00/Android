package com.example.quizapp.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.data.SharedViewModel
import com.example.quizapp.databinding.FragmentQuizEndBinding

class QuizEndFragment : Fragment() {

    private lateinit var binding : FragmentQuizEndBinding
    private lateinit var viewModel: SharedViewModel
    private lateinit var resultPoints : TextView
    private lateinit var tryAgainButton : Button

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizEndBinding.inflate(inflater, container, false)
        val fragment: View =  binding.root

        initializeElements()
        setListeners()
        resultPoints.text = "${viewModel.getNrOfCorrectAnswers()} / 4"
        viewModel.setNrOfCurrentQuestionAndCorrectAnswers()

        return fragment
    }

    private fun initializeElements(){
        resultPoints = binding.tvQuizResultPoints
        tryAgainButton = binding.TryAgainButton
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    private fun setListeners(){
        tryAgainButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_quizEndFragment_to_quizStartFragment)
        }
    }

    override fun onAttach(context : Context){
        super.onAttach(context)
        //callback
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_quizEndFragment_to_quizStartFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}