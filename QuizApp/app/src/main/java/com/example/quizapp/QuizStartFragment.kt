package com.example.quizapp

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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class QuizStartFragment : Fragment() {

    private lateinit var userName  : EditText
    private lateinit var button : Button
    private lateinit var mainLayout : ConstraintLayout
    private lateinit var contactButton : Button
    private lateinit var imageButton : Button
    private lateinit var imageView : ImageView
    private lateinit var email : EditText

    private val getContent = registerForActivityResult(
        ActivityResultContracts.PickContact()) {
        //get contact details
        val cursor1 = context?.contentResolver?.query(it!!, null, null, null, null )
        if(cursor1!!.moveToFirst()) {
            val contactId = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts._ID))
            val cursor2 =  context?.contentResolver?.query(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI,
                null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +  contactId, null, null )
            while(cursor2!!.moveToNext()){
                //set the phone number
                Log.i("most", cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).toString())
            }
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
        val fragment: View =  inflater.inflate(R.layout.fragment_quiz_start, container, false)
        userName  = fragment.findViewById(R.id.userName)
        button = fragment.findViewById(R.id.getStartedButton)
        mainLayout = fragment.findViewById(R.id.mainLayout)

        button.setOnClickListener {
            Log.i(tag, "You pressed the button and entered ${userName.text.toString()}")
            val flag = validateEmailAddress(fragment)
            when {
                userName.text.isEmpty() -> Snackbar.make(
                    mainLayout,
                    "TRY AGAIN",
                    Snackbar.LENGTH_LONG
                )
                    .show()
                userName.text.contains("[0-9]".toRegex()) -> Snackbar.make(
                    mainLayout,
                    "CANCEL",
                    Snackbar.LENGTH_LONG
                )
                    .show()
                else -> { Snackbar.make(mainLayout, "OK", Snackbar.LENGTH_LONG)
                    .show()
//                    val intent = Intent(this, SecondActivity::class.java).apply {
//                        putExtra("MESSAGE", userName.text.toString()) }
//                    if(flag) startActivity(intent)
                }
            }
        }

        contactButton = fragment.findViewById(R.id.chooseContactButton)
        imageButton = fragment.findViewById(R.id.chooseImageButton)

        //Choosing contact
        userName  = fragment.findViewById(R.id.userName)
        contactButton.setOnClickListener{
            getContent.launch(null)
        }

        //Choosing photo
        imageView = fragment.findViewById(R.id.imageView)
        imageButton.setOnClickListener{
            getImage.launch("image/*")
        }
        return fragment
    }

    private fun validateEmailAddress(fragment: View) : Boolean{
        email = fragment.findViewById(R.id.editTextEmailAddress)
        if(email.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            Toast.makeText(context, "Email validated successfully!", Toast.LENGTH_SHORT).show()
            return true
        }
        Toast.makeText(context, "Invalid email!", Toast.LENGTH_SHORT).show()
        return false
    }
}