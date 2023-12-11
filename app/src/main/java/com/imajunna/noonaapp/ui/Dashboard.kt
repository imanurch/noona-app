package com.imajunna.noonaapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityDashboardBinding
import com.imajunna.noonaapp.databinding.ActivityLoginPageBinding

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLessons.setOnClickListener {
            startActivity(Intent(this, Lessons::class.java))
            finish()
        }
        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            finish()
        }
        binding.btnPeriodJournal.setOnClickListener {  startActivity(Intent(this, PeriodJournal::class.java))
            finish() }

        var userData = getUserData()
        binding.textWelcoming.text = "Welcome, ${userData["nama"]}!"
    }

    private fun getUserData(): Map<String, String> {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("localData", Context.MODE_PRIVATE)

        val gson = Gson()
        val json = sharedPreferences.getString("userData", "")

        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(json, type) ?: emptyMap()
    }
}