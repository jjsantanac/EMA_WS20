package com.example.reminderproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    public var userinfo= settings.User(name=null)

    private val switchActivity = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.homescreen -> {
                return@OnNavigationItemSelectedListener false
            }
            R.id.profile -> {
                val intent = Intent(this@MainActivity, settings::class.java)
               // intent.putExtra("user", userinfo)
                startActivity(intent)
                finish()
                return@OnNavigationItemSelectedListener true
            }
            R.id.customize -> {
                val intent = Intent(this@MainActivity, customize::class.java)
                intent.putExtra("user", userinfo)
                startActivity(intent)
                finish()
                return@OnNavigationItemSelectedListener true
            }
            R.id.uebungen -> {
                val intent = Intent(this@MainActivity, uebungen::class.java)
                intent.putExtra("user", userinfo)
                startActivity(intent)
                finish()
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }
    private fun loadData(){
        val sharedPreferences=getSharedPreferences("user_settings",Context.MODE_PRIVATE)
        val user_name=sharedPreferences.getString("user_name",null)
        val user_age=sharedPreferences.getInt("user_age",0)
        val user_height=sharedPreferences.getInt("user_height",0)
        val user_weight=sharedPreferences.getInt("user_weight",0)
        val movement_status=sharedPreferences.getBoolean("movement_reminder",true)
        val posture_status=sharedPreferences.getBoolean("posture_reminder",true)
        val drink_status=sharedPreferences.getBoolean("drink_reminder",true)
        userinfo.name=user_name
        userinfo.age=user_age
        userinfo.height=user_height
        userinfo.weight=user_weight
        userinfo.move=movement_status
        userinfo.posture=posture_status
        userinfo.drink=drink_status
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var quotes = mutableListOf(getString(R.string.zitat1),
            getString(R.string.zitat2),
            getString(R.string.zitat3),
            getString(R.string.zitat4),
            getString(R.string.zitat5),
            getString(R.string.zitat6),
            getString(R.string.zitat7),
            getString(R.string.zitat8),
            getString(R.string.zitat9),
            getString(R.string.zitat10),
            getString(R.string.zitat11),
            getString(R.string.zitat12),
            getString(R.string.zitat13),
            getString(R.string.zitat14),
            getString(R.string.zitat15)
        )
        val quotes_index=(0..14).random()
        val scaleUp=AnimationUtils.loadAnimation(this,R.anim.scale_up)
        val scaleDown=AnimationUtils.loadAnimation(this,R.anim.scale_down)





        val sharedPreferences=getSharedPreferences("user_settings",Context.MODE_PRIVATE)
        val button_state=sharedPreferences.getBoolean("button_state",false)


        loadData()

        val text= findViewById<TextView>(R.id.qoutes)
        text.text="\""+quotes[quotes_index]+"\""


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botom_navigation)
        bottomNavigation.selectedItemId = R.id.homescreen

        bottomNavigation.setOnNavigationItemSelectedListener(switchActivity)

        createNotificationChannel("bewegung","101")
        createNotificationChannel("haltung","102")
        createNotificationChannel("trinken","103")

        val button_notify = findViewById<Button>(R.id.button_notification)

        if(button_state){
            button_notify.text="ON"
        }
        else{
            button_notify.text="OFF"
        }

        button_notify.setOnClickListener {
            val button_status = button_notify.text.toString()
            if(button_status=="ON"){
                button_notify.text="OFF"
                SplashActivity.Companion.timer_move.cancel()
                SplashActivity.Companion.timer_drink.cancel()
                SplashActivity.Companion.timer_posture.cancel()
                SaveButtonState(false)
            }
            else{
                button_notify.text="ON"
                SaveButtonState(true)
                if(userinfo.move){
                    SplashActivity.Companion.timer_move.start()
                }
                if(userinfo.drink){
                    SplashActivity.Companion.timer_drink.start()
                }
                if(userinfo.posture){
                    SplashActivity.Companion.timer_posture.start()
                }
            }
            button_notify.startAnimation(scaleDown)
            button_notify.startAnimation(scaleUp)
        }


    }

    lateinit var channel_id:String
    var notificationId=100

    private fun createNotificationChannel(channel_name:String,channelid:String) {
         channel_id =channelid

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = channel_name
            val descriptionText = "Das hast du noch zu tun"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channel_id, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

    fun sendNotification(channelid:String) {
        var title="hallo"
        var content="hallo"

        when(channelid){
            "101" ->{
                title=getString(R.string.bewegungs_title)
                content=getString(R.string.bewegungs_content)
                notificationId=101
            }
            "102" ->{
                title=getString(R.string.haltung_title)
                content=getString(R.string.haltung_content)
                notificationId=102
            }
            "103" ->{
                title=getString(R.string.trinken_title)
                content=getString(R.string.trinken_content)
                notificationId=103
            }
        }
        val builder = NotificationCompat.Builder(this, channelid)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

    private fun SaveButtonState(state:Boolean){
        val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putBoolean("button_state",state)
        }.apply()
    }
}