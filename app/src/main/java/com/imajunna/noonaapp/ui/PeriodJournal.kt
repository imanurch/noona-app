package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityLoginPageBinding
import com.imajunna.noonaapp.databinding.ActivityPeriodJournalBinding

class PeriodJournal : AppCompatActivity() {
    private lateinit var binding: ActivityPeriodJournalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeriodJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddJournal.setOnClickListener{
            startActivity(Intent(this,AddJournal::class.java))
            finish()
        }
    }
}