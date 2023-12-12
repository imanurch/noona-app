package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityExercise2Binding
import com.imajunna.noonaapp.databinding.ActivityExerciseBinding

class Exercise2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityExercise2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_exercise2)

        binding = ActivityExercise2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, FactOrMythActivity::class.java))
            finish()
        }

        binding.prev.setOnClickListener {
            startActivity(Intent(this, ExerciseActivity::class.java))
            finish()
        }
    }
}