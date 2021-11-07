package com.example.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.adapter.RecyclerViewAdapter
import com.example.quizapp.data.SharedViewModel
import com.example.quizapp.databinding.FragmentQuestionListBinding

class QuestionListFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding : FragmentQuestionListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel : SharedViewModel

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
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        recyclerView = binding.recyclerView
        recyclerView.adapter = RecyclerViewAdapter(viewModel.getQuestions(), this)
        recyclerView.setHasFixedSize(true)
    }

    private fun setListeners(){
    }

//    override fun onItemClick(position: Int) {
//        viewModel.setCurrentQuestionPosition(position)
//        findNavController().navigate(R.id.action_questionListFragment_to_questionDetailFragment)
//    }

    override fun onDetailsButtonClick(position: Int) {
        viewModel.setCurrentQuestionPosition(position)
        findNavController().navigate(R.id.action_questionListFragment_to_questionDetailFragment)
    }

    override fun onDeleteButtonClick(position: Int) {
        viewModel.deleteQuestion(position)
        (recyclerView.adapter as RecyclerViewAdapter).notifyDataSetChanged()
    }

}