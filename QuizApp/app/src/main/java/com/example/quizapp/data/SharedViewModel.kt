package com.example.quizapp.data

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.quizapp.R
import com.example.quizapp.model.Question
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class SharedViewModel(application : Application) : AndroidViewModel(application) {

    private val questions = mutableListOf<Question>()
    private var currentQuestion = 0
    private var correctAnswers = 0
    private var playerName : String = ""
    private var highestScore : Int = 0
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext

    init {
        loadQuestions()
    }

    fun increaseNrOfCorrectAnswers() {
        correctAnswers++
    }

    fun getNrOfCorrectAnswers(): Int {
        return correctAnswers
    }
    fun getQuestions(): MutableList<Question> {
        return questions
    }
    fun getFourRandomQuestion(): MutableList<Question> {
        return questions.subList(0, 4)
    }

    fun getCurrentQuestion(): Question {
        return questions[currentQuestion]
    }

    fun getNextQuestion(): Question {
        return questions[++currentQuestion]
    }

    fun isLastQuestion(): Boolean {
        return currentQuestion == 3
    }

    fun setNrOfCurrentQuestionAndCorrectAnswers(){
        currentQuestion = 0
        correctAnswers = 0
    }

    fun loadQuestions() {
        currentQuestion = 0
        correctAnswers = 0
        val isReader: InputStream = context.resources.openRawResource(R.raw.questions)
        val reader = BufferedReader(InputStreamReader(isReader))
        val lines = reader.readLines()
        for(i in 0..lines.size-5 step 5){
            if(i % 5 == 0){
                questions.add(Question(lines[i],
                                        listOf(Pair(lines[i+1], true), Pair(lines[i+2], false), Pair(lines[i+3], false), Pair(lines[i+4], false))))
            }
        }
    }

    fun setPlayerName(playerName : String){
        this.playerName = playerName
    }

    fun getPlayerName(): String? {
        return playerName
    }

    fun setHighestScore(){
        if(highestScore < correctAnswers) highestScore =  correctAnswers
    }

    fun getHighestScore() : Int {
        return highestScore
    }
    fun addNewQuestion(text : String, ans1 : String, ans2 : String, ans3 : String, ans4 : String){
        questions.add(Question(text, listOf(Pair(ans1, true), Pair(ans2, false), Pair(ans3, false), Pair(ans4, false))))
    }
    fun setCurrentQuestionPosition(poz : Int){
        currentQuestion = poz
    }
}