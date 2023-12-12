package com.imajunna.noonaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityDashboardBinding

class FactOrMythActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_fact_or_myth)

        binding = FactOrMythActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnLessons.setOnClickListener {
//            startActivity(Intent(this, Lessons::class.java))
//            finish()
//        }
    }
}