package com.example.gauravarya.Windy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import android.os.Handler
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import android.view.animation.AnimationUtils
import com.example.gauravarya.windy.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //5 seconds

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        textView3.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(this,R.anim.fadein)
        textView3.startAnimation(animation)

        // Thread.sleep(1_000)
        //val animat = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //textView3.startAnimation(animat)


        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}