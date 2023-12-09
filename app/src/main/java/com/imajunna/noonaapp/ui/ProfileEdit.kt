package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityProfileBinding
import com.imajunna.noonaapp.databinding.ActivityProfileEditBinding

class ProfileEdit : AppCompatActivity() {
    private lateinit var binding: ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpan.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            finish()
        }
        binding.btnBatal.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            finish()
        }
    }
}