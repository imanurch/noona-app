package com.imajunna.noonaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.imajunna.noonaapp.databinding.ActivityMainBinding
import com.imajunna.noonaapp.ui.Dashboard
import com.imajunna.noonaapp.ui.LoginPage

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val currentUser = auth.currentUser

        if (currentUser != null) {
            startActivity(Intent(this, Dashboard::class.java))
        } else {
            startActivity(Intent(this, LoginPage::class.java))
        }

    }
}