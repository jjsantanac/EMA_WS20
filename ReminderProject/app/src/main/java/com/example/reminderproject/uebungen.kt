package com.example.reminderproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.RecyclerView

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






    fun browseuebungen(){
        val transaction = supportFragmentManager.beginTransaction()
        val fragment=browse_uebuengen()
        transaction.replace(R.id.uebungen_tab, fragment)
        transaction.commit()
    }

    fun LoadNotificationCounter(): Int{
        val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)

        return sharedPreferences.getInt("notification_count",0)
    }

    //private var notification_counter=LoadNotificationCounter()

    //private var historyList=generateDummyList(notification_counter)
    //private val adapter=HistoryViewAdapter(historyList,this)

    lateinit var historyList:ArrayList<historyItem>
    lateinit var adapter:HistoryViewAdapter

    fun OpenBrowseUebungenActivity(){
        val intent = Intent(this@uebungen, browse_uebungen_a::class.java)
        startActivity(intent)
    }

    override fun onImageClick(position: Int) {

        //Toast.makeText(this,"Item $position clicked",Toast.LENGTH_SHORT).show()
        historyList.removeAt(position)
        adapter.notifyDataSetChanged()

        val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
        var notification_counter=sharedPreferences.getInt("notification_count",0)
        val editor = sharedPreferences.edit()
        editor.apply{
            putInt("notification_count",notification_counter-1)
        }.apply()
    }

    fun CreateHistoryList(notification_counter:Int){
        historyList=generateDummyList(notification_counter)
        adapter=HistoryViewAdapter(historyList,this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uebungen)

        CreateHistoryList(LoadNotificationCounter())

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

            val workout_number=(1..3).random()
            val item = historyItem( "Time to stretch!", "Complete workout $workout_number.")
            list += item
        }
        return list
    }
}