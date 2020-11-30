package com.example.reminderproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class SplashActivity : AppCompatActivity() {
    companion object{
        lateinit var timer_move: CountDownTimer
        lateinit var timer_posture: CountDownTimer
        lateinit var timer_drink: CountDownTimer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        timer_move= object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                sendNotification("101")

                val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
                var notification_counter=sharedPreferences.getInt("notification_count",0)
                val editor = sharedPreferences.edit()
                editor.apply{
                    putInt("notification_count",notification_counter+1)
                }.apply()
                this.start()
            }


        }
        timer_posture= object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                sendNotification("102")

                val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
                var notification_counter=sharedPreferences.getInt("notification_count",0)
                val editor = sharedPreferences.edit()
                editor.apply{
                    putInt("notification_count",notification_counter+1)
                }.apply()
                this.start()
            }


        }
        timer_drink= object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                sendNotification("103")

                val sharedPreferences = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
                var notification_counter=sharedPreferences.getInt("notification_count",0)
                val editor = sharedPreferences.edit()
                editor.apply{
                    putInt("notification_count",notification_counter+1)
                }.apply()
                this.start()
            }


        }


        val yoga=findViewById<ImageView>(R.id.yogadude)
        yoga.alpha=0f
        yoga.animate().setDuration(3000).alpha(1f).withEndAction{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()

        }

    }
    var notificationId=100

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
}