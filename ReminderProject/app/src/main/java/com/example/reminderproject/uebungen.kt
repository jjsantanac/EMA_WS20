package com.example.reminderproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.RecyclerView

class uebungen : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val switchActivity = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.homescreen -> {
                val intent = Intent(this@uebungen, MainActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                val intent = Intent(this@uebungen, settings::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.customize -> {
                val intent = Intent(this@uebungen, customize::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.uebungen -> {

                return@OnNavigationItemSelectedListener false
            }
        }
        false

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uebungen)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botom_navigation)
        bottomNavigation.selectedItemId=R.id.uebungen

        bottomNavigation.setOnNavigationItemSelectedListener(switchActivity)

        val historyList=generateDummyList(10)

        val historyview=findViewById<RecyclerView>(R.id.historyview)

        historyview.adapter=HistoryViewAdapter(historyList)
        historyview.layoutManager=LinearLayoutManager(this)
        historyview.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): List<historyItem> {
        val list = ArrayList<historyItem>()
        for (i in 0 until size) {

            val item = historyItem( "Item $i", "Content 2")
            list += item
        }
        return list
    }
}