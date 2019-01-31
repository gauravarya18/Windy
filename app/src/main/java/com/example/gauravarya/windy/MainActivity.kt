package com.example.gauravarya.Windy

import android.app.DownloadManager

import android.view.View
import android.widget.EditText
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import java.io.File
import android.widget.TextView
import android.widget.Toast
import com.example.gauravarya.windy.R
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.IOException


import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MediaPlayer.OnCompletionListener {
    lateinit var recorder: MediaRecorder
    lateinit var player: MediaPlayer
    lateinit var file: File
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var tv1: TextView
    // coding180.com
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var r = 0
        var k = 0
        var count = 0
        tv1 = findViewById(R.id.tvl) as TextView
        button1 = findViewById(R.id.button1) as Button



        button1.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
            button1.startAnimation(animation)

            val animatio = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
            button1.startAnimation(animatio)

            if (r == 0) {
                k = 0

                recorder = MediaRecorder()
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                val path = File(Environment.getExternalStorageDirectory().getPath())
                try {
                    file = File.createTempFile("temporary", ".3gp", path)
                } catch (e: IOException) {
                }

                recorder.setOutputFile(file.absolutePath)
                try {
                    recorder.prepare()
                } catch (e: IOException) {
                }
                button1.text = "Stop"
                recorder.start()
                recorder.maxAmplitude
                tv1.text = "Recording.."

                r = 1
                button1.setEnabled(true)
            } else if (r == 1) {
                k = recorder.maxAmplitude

                recorder.stop()
                recorder.release()
                player = MediaPlayer()
                player.setOnCompletionListener(this)
                try {
                    player.setDataSource(file.absolutePath)
                } catch (e: IOException) {
                }

                try {
                    player.prepare()
                } catch (e: IOException) {
                }

                button1.setEnabled(true)



                button1.text = "Play"
                tv1.text = "Ready to play"
                count = 1
                r = 2


            } else if (count == 1) {
                player.start();

                tv1.setText("Playing...");
                r = 0
                count = 0
                button1.text = "Record"
                button1.setEnabled(true)
            }
        }


        button.setOnClickListener() {
            //            Fuel.upload("http://127.0.0.1:8000/").source { request, url ->
//                file
//            }.responseString { request, response, result ->
//
//            }
            // Toast.makeText(this, "Sending request to the server", Toast.LENGTH_SHORT).show()
            //Thread.sleep(4_000)
            val animation = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
            button.startAnimation(animation)

            val animatio = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
            button.startAnimation(animatio)
            if (k > 5000) {
                val intent = Intent(applicationContext, processing::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(applicationContext, processing2::class.java)
                startActivity(intent)
            }


        }

    }

    override fun onCompletion(mp: MediaPlayer) {
        button1.setEnabled(true)
        tv1.setText("Ready")

    }


}