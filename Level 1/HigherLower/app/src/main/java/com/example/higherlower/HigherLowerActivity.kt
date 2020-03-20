package com.example.higherlower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_higher_lower.*

class HigherLowerActivity : AppCompatActivity() {

    private var currentThrow: Int = 1;
    private var lastThrow: Int = 1;
    private val dicePics = intArrayOf(R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_lower)
        initView()
        btnEqual.setOnClickListener{
            onEqualsClick()
        }
        btnLower.setOnClickListener{
            onLowerClick()
        }
        btnHigher.setOnClickListener{
            onHigherClick()
        }
    }

    private fun initView() {
        updateUI();
    }

    private fun updateUI() {
        tvLastThrow.text = getString(R.string.last_throw, lastThrow)
        ivDice.setImageResource(dicePics[currentThrow-1]);
    }

    private fun rollDice() {
        lastThrow = currentThrow;
        currentThrow = (1..6).random()
        updateUI()
    }

    private fun onLowerClick() {
        rollDice()
        if (currentThrow < lastThrow) {
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, getString(R.string.wrong), Toast.LENGTH_LONG).show()
        }
    }

    private fun onEqualsClick() {
        rollDice()
        if (currentThrow == lastThrow) {
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, getString(R.string.wrong), Toast.LENGTH_LONG).show()
        }
    }


    private fun onHigherClick() {
        rollDice()
        if (currentThrow > lastThrow) {
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, getString(R.string.wrong), Toast.LENGTH_LONG).show()
        }
    }
}


