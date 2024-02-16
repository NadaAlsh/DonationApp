package com.nadaalshaibani.donationapp.ui

import android.app.TaskStackBuilder
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat.startActivity
import com.nadaalshaibani.donationapp.MainActivity

class SplashScreen: AppCompatActivity()  {

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        preferences = getSharedPreferences("splash", MODE_PRIVATE)
        editor = preferences.edit()


        Handler(Looper.myLooper()!!).postDelayed(Runnable {

                // Firebase

            if (FirebaseAuth.getInstance().currentUser == null) {
                preferences.getBoolean("isMain", true)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                editor.putBoolean("isMain", true)
                editor.apply()

                TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(Intent(this, MainActivity::class.java))
                    .addNextIntent(Intent(this, IntroActivity::class.java))
                    .startActivities()
            }

        }, 1000)
    }
}