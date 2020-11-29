package com.example.reminderproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView



class customize : AppCompatActivity() {

    var userinfo= settings.User(name=null)

    private fun loadData(movement: CheckBox,drink:CheckBox,posture:CheckBox){
        val sharedPreferences=getSharedPreferences("user_settings", Context.MODE_PRIVATE)
        val movement_status=sharedPreferences.getBoolean("movement_reminder",true)
        val posture_status=sharedPreferences.getBoolean("posture_reminder",true)
        val drink_status=sharedPreferences.getBoolean("drink_reminder",true)
        movement.isChecked=movement_status
        posture.isChecked=posture_status
        drink.isChecked=drink_status
    }

    private fun loadNotes(rowID: String, editText: EditText){
        val sharedPreferences=getSharedPreferences("user_settings", Context.MODE_PRIVATE)
        val text=sharedPreferences.getString(rowID,"")
        editText.setText(text)
    }


    private val switchActivity = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.homescreen -> {
                val intent = Intent(this@customize, MainActivity::class.java)
                intent.putExtra("user", userinfo)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                val intent = Intent(this@customize, settings::class.java)
                intent.putExtra("user", userinfo)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize)
        //userinfo=intent.getSerializableExtra("user") as settings.User
        val move_cb=findViewById<CheckBox>(R.id.movement_cb)
        val drink_cb=findViewById<CheckBox>(R.id.drink_cb)
        val posture_cb=findViewById<CheckBox>(R.id.posture_cb)
        loadData(move_cb,drink_cb,posture_cb)
        move_cb.setOnCheckedChangeListener { buttonView, isChecked ->
            userinfo.move = isChecked
            val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply {
                putBoolean("movement_reminder",userinfo.move)
            }.apply()
            Toast.makeText(this,"Change applied when reminders are reactivated ",Toast.LENGTH_SHORT).show()

        }
        drink_cb.setOnCheckedChangeListener { buttonView, isChecked ->
            userinfo.drink = isChecked
            val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply {
                putBoolean("drink_reminder", userinfo.drink)
            }.apply()

            Toast.makeText(this,"Change applied when reminders are reactivated ",Toast.LENGTH_SHORT).show()

        }

        posture_cb.setOnCheckedChangeListener { buttonView, isChecked ->
            userinfo.posture = isChecked
            val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply {
                putBoolean("posture_reminder", userinfo.posture)
            }.apply()
            val toast=Toast.makeText(this,"Change applied when reminders are reactivated ",Toast.LENGTH_SHORT).show()
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botom_navigation)
        bottomNavigation.selectedItemId=R.id.customize

        bottomNavigation.setOnNavigationItemSelectedListener(switchActivity)

        val editTextRow1=findViewById<EditText>(R.id.edit_notes)
        val editTextRow2=findViewById<EditText>(R.id.edit_notes2)
        val editTextRow3=findViewById<EditText>(R.id.edit_notes3)
        val editTextRow4=findViewById<EditText>(R.id.edit_notes4)
        val editTextRow5=findViewById<EditText>(R.id.edit_notes5)

        loadNotes("1",editTextRow1)
        loadNotes("2",editTextRow2)
        loadNotes("3",editTextRow3)
        loadNotes("4",editTextRow4)
        loadNotes("5",editTextRow5)



        editTextRow1.setOnKeyListener { v, keyCode, event ->

            SaveNotesOnKeyPress("1",editTextRow1.text.toString())

            return@setOnKeyListener true
        }

        editTextRow2.setOnKeyListener { v, keyCode, event ->

            SaveNotesOnKeyPress("2",editTextRow2.text.toString())

            return@setOnKeyListener true
        }

        editTextRow3.setOnKeyListener { v, keyCode, event ->

            SaveNotesOnKeyPress("3",editTextRow3.text.toString())

            return@setOnKeyListener true
        }

        editTextRow4.setOnKeyListener { v, keyCode, event ->

            SaveNotesOnKeyPress("4",editTextRow4.text.toString())

            return@setOnKeyListener true
        }

        editTextRow5.setOnKeyListener { v, keyCode, event ->

            SaveNotesOnKeyPress("5",editTextRow5.text.toString())

            return@setOnKeyListener true
        }

    }

    fun SaveNotesOnKeyPress(rowID:String, content: String){
        val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(rowID, content)
        }.apply()
    }
}