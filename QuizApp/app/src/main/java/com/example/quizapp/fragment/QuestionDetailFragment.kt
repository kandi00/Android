package com.example.quizapp.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.data.SharedViewModel
import com.example.quizapp.databinding.FragmentQuestionDetailBinding
import com.example.quizapp.model.Question

class QuestionDetailFragment : Fragment() {

    private lateinit var binding : FragmentQuestionDetailBinding
    private lateinit var questionTextView: TextView
    private lateinit var answerText1 : TextView
    private lateinit var answerText2 : TextView
    private lateinit var answerText3 : TextView
    private lateinit var answerText4 : TextView
    private lateinit var viewModel : SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionDetailBinding.inflate(inflater, container, false)
        val fragment: View =  binding.root

        initializeElements()
        return fragment
    }

    private fun initializeElements(){
        questionTextView = binding.detailQuestion
        answerText1  = binding.detailAnswer1
        answerText2  = binding.detailAnswer2
        answerText3  = binding.detailAnswer3
        answerText4  = binding.detailAnswer4
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val current = viewModel.getCurrentQuestion()
        questionTextView.text = current.text
        answerText1.text = current.answers[0].first
        answerText2.text = current.answers[1].first
        answerText3.text = current.answers[2].first
        answerText4.text = current.answers[3].first
    }
}