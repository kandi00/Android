package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var userNameTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second2)

        val message = intent.getStringExtra("MESSAGE") ?: ""
        Log.i("UserName", message)

        userNameTextView = findViewById<TextView>(R.id.userName).apply {
                text = message
        }

    }
}