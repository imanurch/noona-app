package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityDashboardBinding
import com.imajunna.noonaapp.databinding.ActivityLessonPageBinding
import com.imajunna.noonaapp.databinding.ActivityLessonsBinding

class Lessons : AppCompatActivity() {
    private lateinit var binding: ActivityLessonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lessoncard1.setOnClickListener {
            startActivity(Intent(this, LessonPage::class.java))
            finish()
        }
    }
}