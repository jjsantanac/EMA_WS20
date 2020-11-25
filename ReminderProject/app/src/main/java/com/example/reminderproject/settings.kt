package com.example.reminderproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.Serializable


public class settings : AppCompatActivity() {


    data class User(var name:String):Serializable{
        var age:Int=0
        var weight:Int=0
        var height:Int=0
        var move:Boolean=true
        var posture:Boolean=true
        var drink:Boolean=true
    }

    lateinit var user_info:User


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
        val intent = Intent(this@settings, edit_profile_activity::class.java)
        intent.putExtra("user",user_info)
        startActivity(intent)

    }

    //private val editInput= View.OnClickListener {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        user_info=intent.getSerializableExtra("user") as User

        val namefield=findViewById<TextView>(R.id.namefield)
        val agefield=findViewById<TextView>(R.id.agefield)
        val heightfield=findViewById<TextView>(R.id.heightfield)
        val weightfield=findViewById<TextView>(R.id.weightfield)


        namefield.text=user_info.name
        agefield.text=user_info.age.toString()
        heightfield.text=user_info.height.toString()
        weightfield.text=user_info.weight.toString()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botom_navigation)
        bottomNavigation.selectedItemId=R.id.profile

        bottomNavigation.setOnNavigationItemSelectedListener(switchActivity)

        val editButton=findViewById<ImageButton>(R.id.imageButton)



        editButton.setOnClickListener({loadFragment()})



    }
}