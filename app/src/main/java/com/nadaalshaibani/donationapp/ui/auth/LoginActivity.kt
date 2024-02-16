package com.nadaalshaibani.donationapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nadaalshaibani.donationapp.MainActivity
import com.nadaalshaibani.donationapp.util.Config

class LoginActivity: AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        binding.userForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotActivity::class.java))
        }

        binding.userRegistion.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.signIn.setOnClickListener {
            val email = binding.userEmail.text.toString()
            val password = binding.userPassword.text.toString()

            if (email.isEmpty()) {
                binding.userEmail.setError("Please Enter your Email")
                binding.userEmail.requestFocus()
            } else if (password.isEmpty()) {
                binding.userPassword.setError("Please Enter your Password")
                binding.userPassword.requestFocus()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    Config.showDialog(this)
                    if (it.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    }else{
                        Config.hideDialog()
                        Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}