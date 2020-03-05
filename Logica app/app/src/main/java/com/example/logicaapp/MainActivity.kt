package com.example.logicaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var score: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            btnSubmit.setOnClickListener {
                if (editText5.text.isNotEmpty() && editText4.text.isNotEmpty() && editText3.text.isNotEmpty() && editText2.text.isNotEmpty()) {
                    checkAnswer()
                    Toast.makeText(
                        this,
                        "the correct number of answers is: " + score,
                        Toast.LENGTH_LONG
                    ).show();
                    score = 0;
                }
            }
    }

    private fun checkAnswer(){
        val answer1 = editText2.text.toString();
        val answer2 = editText3.text.toString();
        val answer3 = editText4.text.toString();
        val answer4 = editText5.text.toString();

        if(answer1 == getString(R.string.tfTrue)){
            score++
        }


        if(answer2 == getString(R.string.tfFalse)){
            score++
        }


        if(answer3 == getString(R.string.tfFalse)){
            score++
        }

        if(answer4 == getString(R.string.tfFalse)){
            score++
        }


    }


}
