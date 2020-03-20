package com.example.swipequiz

data class Quiz(
    var quizText: String,
    var quizAnswer: String
){
    companion object {
            val QUIZ_QUESTIONS = arrayOf(
                "Scott is lief",
                "Iris is lief"
            )
            val QUIZ_ANSWERS = arrayOf(
                "True",
                "False"
            )
        }
}