package com.example.quizapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var userName  : EditText
    private lateinit var button : Button
    private lateinit var mainLayout : ConstraintLayout
    private lateinit var contactButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printMessage()
    }

    override fun onStart(){
        super.onStart()
        Log.i("onStart", "onStart")
        val toast = Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onResume(){
        super.onResume()
        Log.i("onResume", "onResume")
        val toast = Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onPause(){
        super.onPause()
        Log.i("onPause", "onPause")
        val toast = Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onStop(){
        super.onStop()
        Log.i("onStop", "onStop")
        val toast = Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("onDestroy", "onDestroy")
        val toast = Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT)
        toast.show()
    }


    private fun printMessage(){
        userName  = findViewById(R.id.userName)
        button = findViewById(R.id.inputNameButton)
        mainLayout = findViewById(R.id.mainLayout)
        contactButton = findViewById(R.id.chooseButton)

        button.setOnClickListener {
            val text = "Button press detected"
            Log.i(text, "You pressed the button and entered ${userName.text.toString()}")
            when{
                userName.text.isEmpty() ->  Snackbar.make(mainLayout, "TRY AGAIN", Snackbar.LENGTH_LONG)
                                                    .show()
                userName.text.contains("[0-9]".toRegex()) ->  Snackbar.make(mainLayout, "CANCEL", Snackbar.LENGTH_LONG)
                                                                    .show()
                else -> Snackbar.make(mainLayout, "OK", Snackbar.LENGTH_LONG)
                                .show()
            }

            val intent = Intent(this, SecondActivity::class.java).apply{
                putExtra("MESSAGE", userName.text.toString())
            }
            startActivity(intent)
        }

        contactButton.setOnClickListener{

        }


    }
}