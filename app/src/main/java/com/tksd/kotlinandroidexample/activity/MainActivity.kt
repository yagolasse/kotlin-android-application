package com.tksd.kotlinandroidexample.activity

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.tksd.kotlinandroidexample.R
import com.tksd.kotlinandroidexample.adapter.NavDrawerAdapter

class MainActivity : AppCompatActivity(), NavDrawerAdapter.OnItemClickListener {

    /**
     * this fields are used to fill the options of the nav drawer
     */
    private val titles = arrayOf("Home", "Events", "Mail", "Shop", "Travel")

    private val icons = intArrayOf(
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_event_black_24dp,
            R.drawable.ic_mail_outline_black_24dp,
            R.drawable.ic_shopping_cart_black_24dp,
            R.drawable.ic_card_travel_black_24dp
    )

    /**
     * this fields are used to fill the header of the nav drawer
     */
    private val name = "User name"
    private val email = "user@email.com"
    private val profile = R.mipmap.ic_launcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.tool_bar) as Toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val recyclerView = findViewById(R.id.drawer_recycler_view) as RecyclerView
        val adapter = NavDrawerAdapter(titles, icons, name, profile, email)
        val drawerToggle = ActionBarDrawerToggle(this, drawer, R.drawable.ic_arrow_back_white_24dp, R.drawable.ic_menu_white_24dp)

        adapter.onItemClickListener = this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        drawer.addDrawerListener(drawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        //onPostCreate
        drawerToggle.syncState()
    }

    //for drawer list items
    override fun onItemClick(itemView: View, position: Int) {
        val message : String = when (position) {
            0 -> "my profile"
            in 1..5 -> titles[position - 1]
            else -> ""
        }
        Toast.makeText(this, "Clicked $message", Toast.LENGTH_SHORT).show()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
            Configuration.ORIENTATION_PORTRAIT -> Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }
}
