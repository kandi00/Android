package com.example.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizapp.databinding.FragmentQuestionListBinding

class QuestionListFragment : Fragment() {

    private lateinit var binding : FragmentQuestionListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionListBinding.inflate(inflater, container, false)
        val fragment: View =  binding.root

        initializeElements()
        setListeners()
        return fragment
    }

    private fun initializeElements(){
    }

    private fun setListeners(){
    }

}