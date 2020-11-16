package com.example.reminderproject

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.MediaController
import android.widget.VideoView

class demonstrate_uebung : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demonstrate_uebung)

        val video=findViewById<VideoView>(R.id.videoView2)

        val mediacontroller=MediaController(this)

        val source= Uri.parse("android.resource://$packageName/${R.raw.cheststretch}")

        video.setMediaController(mediacontroller)
        video.setVideoURI(source)
        video.start()
    }
}