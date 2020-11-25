package com.example.reminderproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {



    val timer_move = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {
            sendNotification("101")
            this.start()
        }


    }
    val timer_posture = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {
            sendNotification("102")

            this.start()
        }


    }
    val timer_drink = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {
            sendNotification("103")
            this.start()
        }


    }

    public lateinit var userinfo: settings.User

    private val switchActivity = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.homescreen -> {
                return@OnNavigationItemSelectedListener false
            }
            R.id.profile -> {
                val intent = Intent(this@MainActivity, settings::class.java)
                intent.putExtra("user", userinfo)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.customize -> {
                val intent = Intent(this@MainActivity, customize::class.java)
                intent.putExtra("user", userinfo)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.uebungen -> {
                val intent = Intent(this@MainActivity, uebungen::class.java)
                intent.putExtra("user", userinfo)
                startActivity(intent)

                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(userinfo==null){
            userinfo = settings.User(name = "Julian")
        }
        userinfo=intent.getSerializableExtra("user") as settings.User

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botom_navigation)
        bottomNavigation.selectedItemId = R.id.homescreen

        bottomNavigation.setOnNavigationItemSelectedListener(switchActivity)

        createNotificationChannel("bewegung","101")
        createNotificationChannel("haltung","102")
        createNotificationChannel("trinken","103")

        val button_notify = findViewById<Button>(R.id.button_notification)
        button_notify.setOnClickListener {
            val button_status = button_notify.text.toString()
            if(button_status=="ON"){
                button_notify.text="OFF"
                timer_move.cancel()
                timer_drink.cancel()
                timer_posture.cancel()
            }
            else{
                button_notify.text="ON"
                if(userinfo.move){
                    timer_move.start()
                }
                if(userinfo.drink){
                    timer_drink.start()
                }
                if(userinfo.posture){
                    timer_posture.start()
                }
            }
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

    private fun sendNotification(channelid:String) {
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
}