package com.example.reminderproject

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.MediaController
import android.widget.VideoView
import androidx.navigation.navArgs

class demonstrate_uebung : AppCompatActivity() {
    private val args: demonstrate_uebungArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demonstrate_uebung)

        val uebung_name=args.uebungType

        val video=findViewById<VideoView>(R.id.videoView2)

        val mediacontroller=MediaController(this)
        val source:Uri
        when (uebung_name){

            "Chest stretch" -> source= Uri.parse("android.resource://$packageName/${R.raw.cheststretch}")
            "Torso twist" -> source= Uri.parse("android.resource://$packageName/${R.raw.torsotwist}")
            "Upper back stretch" -> source= Uri.parse("android.resource://$packageName/${R.raw.upperbackstretch}")
            "Shouldershrug" -> source= Uri.parse("android.resource://$packageName/${R.raw.shouldershrug}")
            else -> {
                source= Uri.parse("android.resource://$packageName/${R.raw.cheststretch}")
            }
        }


        video.setMediaController(mediacontroller)
        video.setVideoURI(source)
        video.setOnCompletionListener {
            video.start()
        }
        video.start()
    }
}