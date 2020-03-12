package com.example.swipequiz

data class Quiz(
    var quizText: String,
    var quizAnswer: String,
    var quizCorrect: Boolean
){
        companion object {
            val QUIZ_QUESTIONS = arrayOf(
                "Is Scott lief?",
                "Is Iris lief?"
            )
            val QUIZ_ANSWERS = arrayOf(
                "ja",
                "nee"
            )
            val QUIZ_CORRECT = arrayOf(
                true,
                false
            )
        }

}