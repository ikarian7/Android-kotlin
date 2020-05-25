package com.example.level5_task2.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.level5_task2.R
import com.example.level5_task2.ui.add.AddActivity

import kotlinx.android.synthetic.main.activity_main.*

const val ADD_GAME_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
                startAddActivity()
            }
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            return when (item.itemId) {
                R.id.action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }
        }

        private fun initViews() {
            fab.setOnClickListener { view ->
                startAddActivity()
            }
        }

        private fun startAddActivity() {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, ADD_GAME_REQUEST_CODE)
        }
    }
