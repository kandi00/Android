package game

import java.io.File

class QuizController {

    val questions = arrayListOf<Question>()

    init{
        val lines = File("questions.txt").readLines()
        for(i in 0..lines.size-2 step 5) {
            if (i % 5 == 0){
                questions.add( Question(lines[i], mutableListOf(lines[i+1], lines[i+2], lines[i+3], lines[i+4])))
            }
        }
    }

    fun doQuiz(number : Int){
        var correctAnswers = 0
//      Shuffles the questions
        randomizeQuestions()
        //selects a given number of questions
        val currentQuestions = questions.subList(0, number)
//      Performs the quiz:
        for(item in currentQuestions){
            //i. Shows the questions one by one to the user and requests a response.
            println(item.text)
            item.answers.forEach { println(it) }
            val answer = readLine()!!.toInt()
//      Shuffle the answers of each question!
            (item.answers as MutableList).shuffle()
//          ii. Evaluate the response
            if(answer == 1) correctAnswers++
        }
//      Shows the final result in the following format: Correct answers/Total number of answers
        println("Correct answers/Total number of answers: $correctAnswers/${currentQuestions.size}")
    }

    fun randomizeQuestions(){
        questions.shuffle()
    }

}