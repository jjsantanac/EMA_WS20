package com.example.reminderproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioGroup
import com.google.android.material.bottomnavigation.BottomNavigationView

class customize : AppCompatActivity() {

    lateinit var user_info: settings.User

    private val switchActivity = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.homescreen -> {
                val intent = Intent(this@customize, MainActivity::class.java)
                intent.putExtra("user", user_info)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                val intent = Intent(this@customize, settings::class.java)
                intent.putExtra("user", user_info)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.customize -> {

                return@OnNavigationItemSelectedListener false
            }
            R.id.uebungen -> {
                val intent = Intent(this@customize, uebungen::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }
    fun get_checkstate(check_button_name:String,checkbutton_state:Boolean,button:CompoundButton){
        when(check_button_name){
            "move" -> {
                user_info.move = checkbutton_state
            }
            "drink" -> {
                user_info.drink = checkbutton_state
            }
            "posture" ->{
                user_info.posture =checkbutton_state
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize)
        user_info=intent.getSerializableExtra("user") as settings.User
        var move_cb=findViewById<CheckBox>(R.id.movement_cb)
        var drink_cb=findViewById<CheckBox>(R.id.drink_cb)
        var posture_cb=findViewById<CheckBox>(R.id.posture_cb)
        move_cb.setOnCheckedChangeListener{buttonView, isChecked -> user_info.move=isChecked}
        drink_cb.setOnCheckedChangeListener{buttonView, isChecked -> user_info.drink=isChecked}
        posture_cb.setOnCheckedChangeListener{buttonView,isChecked -> user_info.posture=isChecked}

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botom_navigation)
        bottomNavigation.selectedItemId=R.id.customize

        bottomNavigation.setOnNavigationItemSelectedListener(switchActivity)
    }
}