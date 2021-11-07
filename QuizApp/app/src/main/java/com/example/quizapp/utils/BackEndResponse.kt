package com.example.quizapp.utils

class BackEndResponse<T>(val response_code: Int, val results: List<T>) {}