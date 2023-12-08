package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imajunna.noonaapp.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private lateinit var binding:ActivityLoginPageBinding
        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            binding = ActivityLoginPageBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnDaftar.setOnClickListener{
                startActivity(Intent(this,SignUp::class.java))
                finish()
            }

            binding.btnLogin.setOnClickListener {
                startActivity(Intent(this, Dashboard::class.java))
                finish()
            }
    }
}