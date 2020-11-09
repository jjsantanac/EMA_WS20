package com.example.reminderproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent

class MainActivity : AppCompatActivity() {

    public lateinit var userinfo:settings.User

    private val switchActivity = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.homescreen -> {
                return@OnNavigationItemSelectedListener false
            }
            R.id.profile -> {
                val intent = Intent(this@MainActivity, settings::class.java)
                intent.putExtra("user",userinfo)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.customize -> {
                val intent = Intent(this@MainActivity, customize::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.uebungen -> {
                val intent = Intent(this@MainActivity, uebungen::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userinfo= settings.User(name = "Julian")

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botom_navigation)
        bottomNavigation.selectedItemId=R.id.homescreen

        bottomNavigation.setOnNavigationItemSelectedListener(switchActivity)



    }
}