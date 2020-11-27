package com.example.reminderproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class edit_profile_activity : AppCompatActivity() {

    lateinit var userinfo:settings.User

    fun save_changes(){
        val name=findViewById<EditText>(R.id.name).text.toString()
        val age=findViewById<EditText>(R.id.age).text.toString()
        val height=findViewById<EditText>(R.id.height).text.toString()
        val weight=findViewById<EditText>(R.id.weight).text.toString()

        userinfo.name=name
        userinfo.age=age.toInt()
        userinfo.height=height.toInt()
        userinfo.weight=weight.toInt()

        saveData(userinfo.name,userinfo.age,userinfo.height,userinfo.weight)

        val intent = Intent(this@edit_profile_activity, settings::class.java)
        intent.putExtra("user",userinfo)
        startActivity(intent)

    }
    private fun saveData(name:String?,age:Int,height:Int,weight:Int){
        val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("user_name",name)
            putInt("user_age",age)
            putInt("user_height",height)
            putInt("user_weight",weight)
        }.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_activity)



        userinfo=intent.getSerializableExtra("user") as settings.User


        val save_button=findViewById<Button>(R.id.savedata)

        save_button.setOnClickListener({save_changes()})
    }
}