package com.example.reminderproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment

class uebungen : AppCompatActivity(),HistoryViewAdapter.OnImageClickListener {


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

    private var historyList=generateDummyList(5)

    private val adapter=HistoryViewAdapter(historyList,this)

    fun browseuebungen(){
        val transaction = supportFragmentManager.beginTransaction()
        val fragment=browse_uebuengen()
        transaction.replace(R.id.uebungen_tab, fragment)
        transaction.commit()
    }

    fun OpenBrowseUebungenActivity(){
        val intent = Intent(this@uebungen, browse_uebungen_a::class.java)
        startActivity(intent)
    }

    override fun onImageClick(position: Int) {

        Toast.makeText(this,"Item $position clicked",Toast.LENGTH_SHORT).show()
        historyList.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uebungen)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botom_navigation)
        bottomNavigation.selectedItemId=R.id.uebungen

        bottomNavigation.setOnNavigationItemSelectedListener(switchActivity)


        val historyview=findViewById<RecyclerView>(R.id.historyview)

        val browse_button=findViewById<Button>(R.id.browse_button)


        browse_button.setOnClickListener({OpenBrowseUebungenActivity()})




        historyview.adapter=adapter
        historyview.layoutManager=LinearLayoutManager(this)
        historyview.setHasFixedSize(true)

        //val remove_history_item=findViewById<ImageButton>(R.id.removeHistoryitem)

    }

    private fun generateDummyList(size: Int): ArrayList<historyItem> {
        val list = ArrayList<historyItem>()
        for (i in 0 until size) {

            val item = historyItem( "Item $i", "Content 2")
            list += item
        }
        return list
    }
}