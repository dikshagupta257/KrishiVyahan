package com.codingblocksmodules.agrome.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting up the animation on image on splash screen
        val fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.ivAppIcon.startAnimation(fadein)
        binding.ivAppIcon.visibility = View.VISIBLE

        Handler().postDelayed({
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
        },2000)
    }
}