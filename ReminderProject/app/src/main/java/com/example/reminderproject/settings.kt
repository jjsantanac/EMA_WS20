package com.example.reminderproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class settings : AppCompatActivity() {


    data class User(val name:String){
        val age:Int=0
        val weight:Int=0
        val height:Int=0
    }

    private val switchActivity = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.homescreen -> {
                val intent = Intent(this@settings, MainActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {

                return@OnNavigationItemSelectedListener false
            }
            R.id.customize -> {
                val intent = Intent(this@settings, customize::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.uebungen -> {
                val intent = Intent(this@settings, uebungen::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    fun loadFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        val fragment=user_input()
        transaction.replace(R.id.settings, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    //private val editInput= View.OnClickListener {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botom_navigation)
        bottomNavigation.selectedItemId=R.id.profile

        bottomNavigation.setOnNavigationItemSelectedListener(switchActivity)

        val editButton=findViewById<ImageButton>(R.id.imageButton)

        editButton.setOnClickListener({loadFragment()})



    }
}