package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imajunna.noonaapp.LessonsFragment
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityDashboardBinding
import com.imajunna.noonaapp.databinding.ActivityLessonPageBinding

class LessonPage : AppCompatActivity() {
    private lateinit var binding: ActivityLessonPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
            finish()
        }
    }
}