package com.example.reminderproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class edit_profile_activity : AppCompatActivity() {

    lateinit var user_info:settings.User

    fun save_changes(){
        val name=findViewById<EditText>(R.id.name).text.toString()
        val age=findViewById<EditText>(R.id.age).text.toString()
        val height=findViewById<EditText>(R.id.height).text.toString()
        val weight=findViewById<EditText>(R.id.weight).text.toString()

        user_info.name=name
        user_info.age=age.toInt()
        user_info.height=height.toInt()
        user_info.weight=weight.toInt()


        val intent = Intent(this@edit_profile_activity, settings::class.java)
        intent.putExtra("user",user_info)
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_activity)

        user_info=intent.getSerializableExtra("user") as settings.User


        val save_button=findViewById<Button>(R.id.savedata)

        save_button.setOnClickListener({save_changes()})
    }
}