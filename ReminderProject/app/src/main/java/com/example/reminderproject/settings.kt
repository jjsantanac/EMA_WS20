package com.example.reminderproject

import android.content.Context
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


    data class User(var name:String?):Serializable{
        var age:Int=0
        var weight:Int=0
        var height:Int=0
        var move:Boolean=true
        var posture:Boolean=true
        var drink:Boolean=true
    }

    var userinfo=User(name=null)

    private fun loadData(){
        val sharedPreferences=getSharedPreferences("user_settings", Context.MODE_PRIVATE)
        val user_name=sharedPreferences.getString("user_name",null)
        val user_age=sharedPreferences.getInt("user_age",0)
        val user_height=sharedPreferences.getInt("user_height",0)
        val user_weight=sharedPreferences.getInt("user_weight",0)
        userinfo.name=user_name
        userinfo.age=user_age
        userinfo.height=user_height
        userinfo.weight=user_weight
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
        val intent = Intent(this@settings, edit_profile_activity::class.java)
        intent.putExtra("user",userinfo)
        startActivity(intent)

    }

    //private val editInput= View.OnClickListener {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

       // user_info=intent.getSerializableExtra("user") as User
        loadData()

        val namefield=findViewById<TextView>(R.id.namefield)
        val agefield=findViewById<TextView>(R.id.agefield)
        val heightfield=findViewById<TextView>(R.id.heightfield)
        val weightfield=findViewById<TextView>(R.id.weightfield)


        namefield.text=userinfo.name
        agefield.text=userinfo.age.toString()
        heightfield.text=userinfo.height.toString()
        weightfield.text=userinfo.weight.toString()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botom_navigation)
        bottomNavigation.selectedItemId=R.id.profile

        bottomNavigation.setOnNavigationItemSelectedListener(switchActivity)

        val editButton=findViewById<ImageButton>(R.id.imageButton)



        editButton.setOnClickListener({loadFragment()})



    }
}