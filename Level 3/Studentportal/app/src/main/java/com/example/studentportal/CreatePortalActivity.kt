package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_portal.*
import android.widget.Toast


class CreatePortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)

        initViews()
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.portal_create_top_bar)

        btnAddPortal.setOnClickListener{
            onAddPortalClick()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun onAddPortalClick() {
            val portal = Portal(
                etTitle.text.toString(),
                etURL.text.toString()
            )
            var resultIntent = Intent()
            resultIntent.putExtra(PORTAL_EXTRA, portal)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()

    }

    companion object {
        const val PORTAL_EXTRA = "PORTAL_EXTRA"
    }
}