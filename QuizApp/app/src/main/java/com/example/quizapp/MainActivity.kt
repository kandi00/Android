package com.example.quizapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private lateinit var userName  : EditText
    private lateinit var button : Button
    private lateinit var mainLayout : ConstraintLayout
    private lateinit var contactButton : Button
    private lateinit var imageButton : Button
    private lateinit var imageView : ImageView
    private lateinit var email : EditText

    private val getContent = registerForActivityResult(
        ActivityResultContracts.PickContact()) {
        val cursor = contentResolver.query(it!!, null, null, null, null )
        if(cursor!!.moveToFirst()){
            userName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)))
        }
    }

    private val getImage = registerForActivityResult(
        ActivityResultContracts.GetContent()) {
        imageView.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactButton = findViewById(R.id.chooseContactButton)
        imageButton = findViewById(R.id.chooseImageButton)

        //Choosing contact
        userName  = findViewById(R.id.userName)
        contactButton.setOnClickListener{
            getContent.launch(null)
        }

        //Choosing photo
        imageView = findViewById(R.id.imageView)
        imageButton.setOnClickListener{
            getImage.launch("image/*")
        }

        printMessage()
    }

    override fun onStart(){
        super.onStart()
        Log.i(tag, "onStart")
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume(){
        super.onResume()
        Log.i(tag, "onResume")
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause(){
        super.onPause()
        Log.i(tag, "onPause")
        Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop(){
        super.onStop()
        Log.i(tag, "onStop")
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i(tag, "onDestroy")
        val toast = Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun printMessage(){
        userName  = findViewById(R.id.userName)
        button = findViewById(R.id.getStartedButton)
        mainLayout = findViewById(R.id.mainLayout)

        button.setOnClickListener {
            Log.i(tag, "You pressed the button and entered ${userName.text.toString()}")
            val flag = validateEmailAddress()
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
                        val intent = Intent(this, SecondActivity::class.java).apply {
                                            putExtra("MESSAGE", userName.text.toString()) }
                        if(flag) startActivity(intent)
                        }
            }
        }
    }

    private fun validateEmailAddress() : Boolean{
        email = findViewById(R.id.editTextEmailAddress)
        if(email.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            Toast.makeText(applicationContext, "Email validated successfully!", Toast.LENGTH_SHORT).show()
            return true
        }
        Toast.makeText(applicationContext, "Invalid email!", Toast.LENGTH_SHORT).show()
        return false
    }
}