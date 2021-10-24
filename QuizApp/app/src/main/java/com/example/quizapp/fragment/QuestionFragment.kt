package com.example.quizapp.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.data.SharedViewModel
import com.example.quizapp.databinding.FragmentQuestionBinding
import com.example.quizapp.model.Question

class QuestionFragment : Fragment() {

    private lateinit var binding : FragmentQuestionBinding
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var nextSubmitButton : Button
    private lateinit var question : TextView
    private lateinit var answer1 : RadioButton
    private lateinit var answer2 : RadioButton
    private lateinit var answer3 : RadioButton
    private lateinit var answer4 : RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        val fragment: View =  binding.root

        createRadioButtonsDynamically()
        initializeElements()
        setListeners()

        //prepare and shuffle questions
        viewModel.loadQuestions()
        viewModel.getQuestions().shuffle()
        //show first question
        showQuestion(viewModel.getCurrentQuestion())

        return fragment
    }

    private fun showQuestion(question : Question) {
        this.question.text = question.text

        this.answer1.apply {
            isChecked = false
            text = question.answers[0].first
        }
        this.answer2.apply {
            isChecked = false
            text = question.answers[1].first
        }
        this.answer3.apply {
            isChecked = false
            text = question.answers[2].first
        }
        this.answer4.apply {
            isChecked = false
            text = question.answers[3].first
        }
    }

    private fun evaluateCurrentQuestion() {
        val currentQuestion = viewModel.getCurrentQuestion()
        this.answer1.apply {
            if (isChecked && currentQuestion.answers[0].second) viewModel.increaseNrOfCorrectAnswers()
            Log.i("log" ,currentQuestion.answers[0].second.toString())
        }
        this.answer2.apply {
            if (isChecked && currentQuestion.answers[1].second) viewModel.increaseNrOfCorrectAnswers()
            Log.i("log" ,currentQuestion.answers[1].second.toString())
        }
        this.answer3.apply {
            if (isChecked && currentQuestion.answers[2].second) viewModel.increaseNrOfCorrectAnswers()
            Log.i("log" ,currentQuestion.answers[2].second.toString())
        }
        this.answer4.apply {
            if (isChecked && currentQuestion.answers[3].second) viewModel.increaseNrOfCorrectAnswers()
            Log.i("log" ,currentQuestion.answers[3].second.toString())
        }
    }

    private fun initializeElements(){
        nextSubmitButton = binding.nextSubmitButton
        question = binding.question
//        answer1 = binding.answer1
//        answer2 = binding.answer2
//        answer3 = binding.answer3
//        answer4 = binding.answer4
    }

    private fun setListeners(){
        nextSubmitButton.setOnClickListener {
            if (viewModel.isLastQuestion()) {
                //evaluate the question which is visible now
                evaluateCurrentQuestion()
                //navigate to next fragment
                this.findNavController().navigate(R.id.action_questionFragment2_to_quizEndFragment)
            } else {
                //evaluate the question which is visible now
                evaluateCurrentQuestion()
                //put the next question to the screen
                showQuestion(viewModel.getNextQuestion())
            }
        }
    }


    private fun createRadioButtonsDynamically() {
        // Create RadioButton Dynamically
        answer1 = RadioButton(context)
        //setting height and width
        answer1.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        answer2 = RadioButton(context)
        answer2.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        answer3 = RadioButton(context)
        answer3.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        answer4 = RadioButton(context)
        answer4.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        // get RadioGroup
        val radioGroup = binding.radioGroup

        //adding button to the radio group container
        radioGroup.addView(answer1)
        radioGroup.addView(answer2)
        radioGroup.addView(answer3)
        radioGroup.addView(answer4)
    }

    override fun onAttach(context : Context){
        super.onAttach(context)
        val back = AlertDialog.Builder(context)
            .setTitle("Exit")
            .setMessage("Are you sure you want to end this quiz?")
            .setPositiveButton("Yes") { _, _ -> findNavController().navigate(R.id.action_questionFragment2_to_quizEndFragment) }
            .setNegativeButton("No"){ _, _ ->}
            .create()
        //callback
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                back.show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}