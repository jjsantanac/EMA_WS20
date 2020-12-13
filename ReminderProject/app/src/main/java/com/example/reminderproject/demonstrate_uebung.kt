package com.example.reminderproject

import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs


class demonstrate_uebung : AppCompatActivity() {
    private val args: demonstrate_uebungArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demonstrate_uebung)

        val uebung_name=args.uebungType

        val video=findViewById<VideoView>(R.id.videoView2)
        val description=findViewById<TextView>(R.id.description_textView)
        val videocontainer=findViewById<FrameLayout>(R.id.videoview_container)

        videocontainer.clipToOutline = true
        video.clipToOutline = true

        val mediacontroller=MediaController(this)
        val source:Uri
        when (uebung_name){

            "Chest stretch" -> {
                source = Uri.parse("android.resource://$packageName/${R.raw.cheststretch}")
                description.text = getString(R.string.chest_stretch)

            }
            "Torso twist" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.torsotwist}")
                description.text = getString(R.string.torso_twist)

            }
            "Upper back stretch" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.upperbackstretch}")
                description.text = getString(R.string.upper_back)

            }
            "Shouldershrug" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.shouldershrug}")
                description.text = getString(R.string.shouldershrug)

            }
            "Neck stretch" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.neck}")
                description.text = getString(R.string.neck)

            }
            "Wall slides" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.wallslide}")
                description.text = getString(R.string.wallslide)

            }
            "Wrist twist" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.wristtwist}")
                description.text = getString(R.string.wristtwist)

            }
            "Wrist downward stretch" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.wristdownward}")
                description.text = getString(R.string.wristdownward)

            }
            "Wrist upward stretch" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.wristupward}")
                description.text = getString(R.string.wristupward)

            }
            "Hip flexor stretch" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.hipflexor}")
                description.text = getString(R.string.hipflexor)

            }
            "Hamstring stretch" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.hamstring}")
                description.text = getString(R.string.hamstring)

            }
            "Thigh stretch" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.thigh}")
                description.text = getString(R.string.thigh)

            }
            "Glute stretch" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.glute}")
                description.text = getString(R.string.glute)

            }
            "Thigh/Hip flexor stretch" -> {
                source =
                    Uri.parse("android.resource://$packageName/${R.raw.hipthigh}")
                description.text = getString(R.string.hipthigh)

            }
            else -> {
                source= Uri.parse("android.resource://$packageName/${R.raw.cheststretch}")
            }
        }



        video.setVideoURI(source)
        video.setOnCompletionListener {
            video.start()
        }
        video.start()
    }
}