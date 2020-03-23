package com.example.studentportal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_portal.*

class AddPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)

        initViews()

    }

    private fun initViews(){
        buttonAddPortal.setOnClickListener {
            startMainActivity()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}