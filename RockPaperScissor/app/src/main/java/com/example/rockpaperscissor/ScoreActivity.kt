package com.example.rockpaperscissor

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_scores.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScoreActivity : AppCompatActivity() {

    private val history = arrayListOf<RockPaperScissor>()
    private val rpsAdapter = RockPaperScissorAdapter(history)
    private lateinit var rpsRepository : RpsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        rpsRepository = RpsRepository(this)

        initViews()
    }

    private fun initViews(){
       // supportActionBar?.setDisplayHomeAsUpEnabled(true)
       // supportActionBar?.title = "Game History"
        rvScores.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvScores.adapter = rpsAdapter
        rvScores.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        getGamesFromDatabase()

    }

    private fun deleteHistory(){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                rpsRepository.deleteAllGames()
            }
            getGamesFromDatabase()
        }
    }

    private fun getGamesFromDatabase(){
        CoroutineScope(Dispatchers.Main).launch {
            val resultList = withContext(Dispatchers.IO){
                rpsRepository.getAllGames()
            }
            this@ScoreActivity.history.clear()
            this@ScoreActivity.history.addAll(resultList)
            this@ScoreActivity.rpsAdapter.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_deleteHistory -> {
                deleteHistory()
                true
            }
            android.R.id.home -> {
                val resultIntent = Intent()
                setResult(Activity.RESULT_OK,resultIntent)
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }
}