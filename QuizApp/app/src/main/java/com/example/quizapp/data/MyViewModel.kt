package com.example.quizapp.data

import androidx.lifecycle.ViewModel
import com.example.quizapp.model.Question

class SharedViewModel : ViewModel() {

    private val questions = mutableListOf<Question>()
    private var currentQuestion = 0
    private var correctAnswers = 0

    fun increaseNrOfCorrectAnswers() {
        correctAnswers++
    }

    fun getNrOfCorrectAnswers(): Int {
        return correctAnswers
    }

    fun getNrOfQuestions(): Int {
        return questions.size
    }

    fun getQuestions(): MutableList<Question> {
        return questions
    }

    fun getCurrentQuestion(): Question {
        return questions[currentQuestion]
    }

    fun getNextQuestion(): Question {
        return questions[++currentQuestion]
    }

    fun isLastQuestion(): Boolean {
        return currentQuestion == questions.size-1
    }

    fun loadQuestions() {
        questions.clear()
        currentQuestion = 0
        correctAnswers = 0
        // Do an asynchronous operation to fetch questions.
        questions.add(Question("All classes in Kotlin classes are by default?",
            listOf(Pair("public", false), Pair("final", true), Pair("sealed", false), Pair("abstract", false))))
        questions.add(Question("What is correct way to create an arraylist in Kotlin?",
            listOf(Pair("val map = hashMapOf(1 to 'one', 2 to 'two', 3 to 'three')", false),
        Pair("enum class Color {RED, GREEN, BLUE}", false), Pair("val list = arrayListOf(1, 2, 3)", true), Pair("val set = hashSetOf(1, 2, 3)", false))))
        questions.add(Question("What is an immutable variable?",
                    listOf( Pair("A variable that cannot change, read-only", true), Pair("A variable that can be changed", false),
                        Pair("A variable used for string interpolation", false), Pair("A simple variable", false))))
        questions.add(Question("Which of the followings constructors are available in Kotlin?",
        listOf(Pair("Primary constructor", false), Pair("Secondary constructor", false), Pair("Both 1 & 2", true), Pair("None of the above", false))))
//        "Which of the following extension methods are used in Kotlin?"
//        "Read texts () & Headlines ()"
//        "Buffer reader ()"
//        "Read each line ()"
//        "All of the above"
//        "There are two types of constructors in Kotlin which are-"
//        "Primary & Secondary constructor"
//        "Default & No-arg constructor"
//        "Parameterized & constant Constructor"
//        "None of the above"
//        "Which of the following is not the basic data types in Kotlin?"
//        "Numbers"
//        "Strings"
//        "Arrays"
//        "Lists"
    }
}