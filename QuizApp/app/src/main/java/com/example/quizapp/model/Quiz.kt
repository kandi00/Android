package com.example.quizapp.model

data class Question( val text : String, val answers : List<Pair<String, Boolean>>)

data class QuestionModel (
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
)