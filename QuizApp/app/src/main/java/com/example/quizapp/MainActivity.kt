package com.example.quizapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printmessage()
    }

    fun printmessage(){

        val userName  = findViewById<EditText>(R.id.userName)
        val button = findViewById<Button>(R.id.inputNameButton)
        val mainLayout = findViewById<ConstraintLayout>(R.id.mainLayout)

        button.setOnClickListener {
            val text = "Button press detected"
            Log.i(text, "You pressed the button and entered ${userName.text}")
            when{
                userName.text.isEmpty() ->  Snackbar.make(mainLayout, "TRY AGAIN", Snackbar.LENGTH_LONG)
                                                    .show()
                userName.text.contains("[0-9]".toRegex()) ->  Snackbar.make(mainLayout, "CANCEL", Snackbar.LENGTH_LONG)
                                                                    .show()
                else -> Snackbar.make(mainLayout, "OK", Snackbar.LENGTH_LONG)
                                .show()
            }
        }
    }
}