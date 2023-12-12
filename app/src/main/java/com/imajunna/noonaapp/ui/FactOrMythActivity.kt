package com.imajunna.noonaapp.ui

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

//        binding.btnLessons.setOnClickListener {
//            startActivity(Intent(this, Lessons::class.java))
//            finish()
//        }
    }
}