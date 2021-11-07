package com.example.quizapp.repository

import com.example.quizapp.api.RetrofitInstance
import com.example.quizapp.model.QuestionModel
import com.example.quizapp.utils.BackEndResponse
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<BackEndResponse<QuestionModel>> {
        return RetrofitInstance.api.getPost()
    }
}