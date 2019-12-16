package com.github.maxapp.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.github.maxapp.presentation.menuprincipal.ServiceActivity
import com.github.maxapp.R


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val handle = Handler()
        handle.postDelayed({ chamarMenuPrincipal() }, 3000)

    }

    private fun chamarMenuPrincipal() {
        startActivity(Intent(applicationContext, ServiceActivity::class.java))
    }


}
