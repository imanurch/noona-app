package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityLessonPageBinding
import com.imajunna.noonaapp.databinding.ActivityLessonsBinding
import com.imajunna.noonaapp.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this, ProfileEdit::class.java))
            finish()
        }
        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }
    }
}