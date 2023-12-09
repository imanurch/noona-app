package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityLoginPageBinding
import com.imajunna.noonaapp.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMasuk.setOnClickListener{
            startActivity(Intent(this,LoginPage::class.java))
            finish()
        }
        binding.btnDaftar.setOnClickListener{
            startActivity(Intent(this,LoginPage::class.java))
            finish()
        }

    }
}