package com.example.quizapp.api

import com.example.quizapp.model.QuestionModel
import com.example.quizapp.utils.BackEndResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuizApi {

    @GET("api.php?amount=10") //endpoint
    suspend fun getPost(): Response<BackEndResponse<QuestionModel>>

}