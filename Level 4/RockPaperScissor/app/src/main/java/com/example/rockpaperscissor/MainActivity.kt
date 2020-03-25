package com.example.rockpaperscissor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {

    private val history = arrayListOf<RockPaperScissor>()
    private lateinit var rpsRepository : RpsRepository

    private var computerChoice: Int = 0
    private var playerChoice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Rock, Paper, Scissors Kotlin"
        rpsRepository = RpsRepository(this)

        initViews()

    }

    private fun initViews(){
        btnPaper.setOnClickListener{ playPaper() }
        btnRock.setOnClickListener{ playRock() }
        btnScissors.setOnClickListener{ playScissors() }
    }


    private fun addGamesToDatabase(){
        CoroutineScope(Dispatchers.Main).launch {
            val rps = RockPaperScissor(
                winner = tvResult.text.toString(),
                computerChoice = computerChoice,
                playerChoice = playerChoice,
                date = Date().toString()
            )
            withContext(Dispatchers.IO){
                rpsRepository.insertGame(rps)
            }
        }
    }

    private fun playPaper(){
        ivPlayer.setImageResource(R.drawable.paper)
        playerChoice = 1
        generateComputerMove()
        playGame(playerChoice, computerChoice)
    }
    private fun playRock(){
        ivPlayer.setImageResource(R.drawable.rock)
        playerChoice = 2
        generateComputerMove()
        playGame(playerChoice, computerChoice)
    }
    private fun playScissors(){
        ivPlayer.setImageResource(R.drawable.scissors)
        playerChoice = 3
        generateComputerMove()
        playGame(playerChoice, computerChoice)
    }

    private fun generateComputerMove(){
        computerChoice = (1..3).random()
        when(computerChoice){
            1 -> ivComputer.setImageResource(R.drawable.paper)
            2 -> ivComputer.setImageResource(R.drawable.rock)
            3 -> ivComputer.setImageResource(R.drawable.scissors)
        }
    }

    private fun playGame(playerMove: Int, computerMove: Int){

        if (computerMove == playerMove){
            tvResult.text = "Draw!"
        }
        else if (computerMove == 1 && playerMove == 2){
            tvResult.text = "Computer Wins!"
        }
        else if (computerMove == 1 && playerMove == 3){
            tvResult.text = "You Win!"
        }
        else if (computerMove == 2 && playerMove == 1){
            tvResult.text = "You Win!"
        }
        else if (computerMove == 2 && playerMove == 3){
            tvResult.text = "Computer Wins!"
        }
        else if (computerMove == 3 && playerMove == 1){
            tvResult.text = "Computer Wins!"
        }
        else if (computerMove == 3 && playerMove == 2){
            tvResult.text = "You Win!"
        }
        addGamesToDatabase()
    }

    private fun gotoHistory(){
        val intent = Intent(this,ScoreActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_gotoHistory -> {
                gotoHistory()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }


    }
}