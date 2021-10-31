package com.example.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.data.SharedViewModel
import com.example.quizapp.databinding.FragmentQuestionAddBinding

class QuestionAddFragment : Fragment() {

    private lateinit var binding : FragmentQuestionAddBinding
    private lateinit var addedAnswer1 : EditText
    private lateinit var addedAnswer2 : EditText
    private lateinit var addedAnswer3 : EditText
    private lateinit var addedAnswer4 : EditText
    private lateinit var addedQuestion : EditText
    private lateinit var button : Button
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionAddBinding.inflate(inflater, container, false)
        val fragment: View =  binding.root

        initializeElements()
        setListeners()
        return fragment
    }

    private fun initializeElements(){
        addedAnswer1 = binding.addedAnswer1
        addedAnswer2 = binding.addedAnswer2
        addedAnswer3 = binding.addedAnswer3
        addedAnswer4 = binding.addedAnswer4
        addedQuestion = binding.addedQuestion
        button = binding.button
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    private fun setListeners(){
        button.setOnClickListener {
            viewModel.addNewQuestion(addedQuestion.text.toString(), addedAnswer1.text.toString(), addedAnswer2.text.toString(), addedAnswer3.text.toString(), addedAnswer4.text.toString())
        }
        Toast.makeText(activity?.applicationContext, "Question added!", Toast.LENGTH_SHORT).show()
    }
}