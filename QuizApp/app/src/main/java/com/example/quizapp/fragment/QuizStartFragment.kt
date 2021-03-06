package com.example.quizapp.fragment

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.data.SharedViewModel
import com.example.quizapp.databinding.FragmentQuizStartBinding
import com.google.android.material.snackbar.Snackbar

class QuizStartFragment : Fragment() {

    private lateinit var binding : FragmentQuizStartBinding
    private lateinit var userName  : EditText
    private lateinit var userEmail : EditText
    private lateinit var button : Button
    private lateinit var contactButton : Button
    private lateinit var imageButton : Button
    private lateinit var imageView : ImageView
    private lateinit var viewModel : SharedViewModel

    private val getContent = registerForActivityResult(
        ActivityResultContracts.PickContact()) {
        //get contact details
        val cursor = context?.contentResolver?.query(it!!, null, null, null, null )
        if(cursor!!.moveToFirst()){
            userName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)))
        }
    }

    private val getImage = registerForActivityResult(
        ActivityResultContracts.GetContent()) {
        imageView.setImageURI(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizStartBinding.inflate(inflater, container, false)
        val fragment: View =  binding.root
        initializeElements()
        setListeners()
        return fragment
    }

    private fun initializeElements(){
        userName  = binding.userName
        button = binding.getStartedButton
        contactButton = binding.chooseContactButton
        imageButton = binding.chooseImageButton
        imageView = binding.quizTime
        userEmail = binding.editTextEmailAddress
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        userName.setText(viewModel.getPlayerName())
        userEmail.setText(viewModel.getPlayerEmail())
    }

    private fun setListeners(){
        button.setOnClickListener {
            Log.i(tag, "You pressed the button and entered ${userName.text}")
            val flag = validateEmailAddress()
            when {
                userName.text.isEmpty() -> Snackbar.make(
                    binding.root,
                    "TRY AGAIN",
                    Snackbar.LENGTH_LONG
                )
                    .show()
                userName.text.contains("[0-9]".toRegex()) -> Snackbar.make(
                    binding.root,
                    "CANCEL",
                    Snackbar.LENGTH_LONG
                )
                    .show()
                else -> { Snackbar.make(binding.root, "OK", Snackbar.LENGTH_LONG)
                    .show()
                    if(flag) this.findNavController().navigate(R.id.action_quizStartFragment_to_questionFragment2)
                    viewModel.setPlayerName(userName.text.toString())
                    viewModel.setPlayerEmail(userEmail.text.toString())
                }
            }
        }

        //Choosing contact
        contactButton.setOnClickListener{
            getContent.launch(null)
        }

        //Choosing photo
        imageButton.setOnClickListener{
            getImage.launch("image/*")
        }
    }

    private fun validateEmailAddress() : Boolean{
        if(userEmail.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(userEmail.text.toString()).matches()){
            Toast.makeText(context, "Email validated successfully!", Toast.LENGTH_SHORT).show()
            return true
        }
        Toast.makeText(context, "Invalid email!", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onAttach(context : Context){
        super.onAttach(context)
        //callback
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_quizStartFragment_to_homeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}