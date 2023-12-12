package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityDashboardBinding
import com.imajunna.noonaapp.databinding.ActivityFactOrMythBinding

class FactOrMythActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFactOrMythBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_fact_or_myth)

        binding = ActivityFactOrMythBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
            finish()
        }
        binding.btnExercise.setOnClickListener {
            startActivity(Intent(this, ExerciseActivity::class.java))
            finish()
        }
    }
}