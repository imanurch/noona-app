package com.imajunna.noonaapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityProfileBinding
import com.imajunna.noonaapp.databinding.ActivityProfileEditBinding

class ProfileEdit : AppCompatActivity() {
    private lateinit var binding: ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpan.setOnClickListener {
            updateProfil()
        }
        binding.btnBatal.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            finish()
        }

        var userData = getUserData()
        binding.etNama.setText(userData["nama"])
        binding.etEmail.setText(userData["email"])
        binding.etTglLahir.setText(userData["tgl_lahir"])
        binding.etBeratBadan.setText(userData["berat_badan"])
        binding.etTinggiBadan.setText(userData["tinggi_badan"])

    }

    private fun updateProfil() {
        TODO("BIKIN FUNCTION BUAT UPDATE PROFILE")


        startActivity(Intent(this, Profile::class.java))
        finish()
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