package com.example.studentportal

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.studentportal.CreatePortalActivity.Companion.PORTAL_EXTRA

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_PORTAL_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {
    private var customTabHelper: CustomTabHelper = CustomTabHelper()

    private var portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals) { portal : Portal -> portalClicked(portal)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()

        fabAdd.setOnClickListener {
            onAddClick()
        }
    }

    private fun initViews() {
        rvPortals.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvPortals.adapter = portalAdapter
        
    }

    private fun onAddClick() {
        val portalAddActivityIntent = Intent(this, CreatePortalActivity::class.java)
        startActivityForResult(portalAddActivityIntent, ADD_PORTAL_REQUEST_CODE)
    }

    private fun portalClicked(portal : Portal) {
        Toast.makeText(this, "Clicked: ${portal.portalTitle}", Toast.LENGTH_LONG).show()

        val builder = CustomTabsIntent.Builder()
        // modify toolbar color
        builder.setToolbarColor(ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))
        // add share button to overflow menu
        builder.addDefaultShareMenuItem()

        val anotherCustomTab = CustomTabsIntent.Builder().build()

        val requestCode = 100
        val intent = anotherCustomTab.intent
        intent.setData(Uri.parse(portal.portalUrl))

        val pendingIntent = PendingIntent.getActivity(this,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT)


        // add menu item to oveflow
        builder.addMenuItem("MENU_ITEM_NAME", pendingIntent)

        // show website title
        builder.setShowTitle(true)

        // animation for enter and exit of tab
        builder.setStartAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
        builder.setExitAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
        //By default, if we don’t set any animations then the Custom Tab will enter from the Bottom to the Top and exit from the Top to the Bottom.

        val customTabsIntent = builder.build()

        // check is chrome available
        val packageName = customTabHelper.getPackageNameToUse(this, portal.portalUrl)
        if (packageName == null)
        // if chrome not available open in web view
        else {
            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.launchUrl(this, Uri.parse(portal.portalUrl))
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

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_REQUEST_CODE -> {
                    val portal = data!!.getParcelableExtra<Portal>(PORTAL_EXTRA)
                    portals.add(portal)
                    portalAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}
