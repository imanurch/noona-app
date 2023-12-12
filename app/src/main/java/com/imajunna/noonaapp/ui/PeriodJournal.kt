package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityLoginPageBinding
import com.imajunna.noonaapp.databinding.ActivityPeriodJournalBinding

class PeriodJournal : AppCompatActivity() {
    private lateinit var binding: ActivityPeriodJournalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeriodJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddJournal.setOnClickListener{
            startActivity(Intent(this,AddJournal::class.java))
            finish()
        }
        val bottomNavigationView =  findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController= findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)
    }
}