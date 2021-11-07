package com.example.quizapp.api

import com.example.quizapp.utils.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//singleton
object RetrofitInstance {

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api : QuizApi by lazy{
        retrofit.create(QuizApi::class.java)
    }
}