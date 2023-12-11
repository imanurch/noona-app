package com.imajunna.noonaapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityProfileBinding
import com.imajunna.noonaapp.databinding.ActivityProfileEditBinding

class ProfileEdit : AppCompatActivity() {
    private lateinit var binding: ActivityProfileEditBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Firebase.firestore


        binding.btnBatal.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            finish()
        }

        var userData = getUserData()


        binding.etNama.setText(userData["nama"])
        binding.textEmail.text = userData["email"]
        binding.etTglLahir.setText(userData["tgl_lahir"])
        binding.etBeratBadan.setText(userData["berat_badan"])
        binding.etTinggiBadan.setText(userData["tinggi_badan"])

        binding.btnSimpan.setOnClickListener {
            var nama = binding.etNama.text.toString()
            var email = binding.textEmail.text.toString()
            var tglLahir = binding.etTglLahir.text.toString()
            var beratBadan = binding.etBeratBadan.text.toString()
            var tinggiBadan = binding.etTinggiBadan.text.toString()

            updateProfil(nama, email, tglLahir, beratBadan, tinggiBadan)
        }
    }

    private fun updateProfil(nama: String, email: String, tglLahir: String, beratBadan: String, tinggiBadan: String) {

        val user = hashMapOf(
            "nama" to nama,
            "email" to email,
            "tgl_lahir" to tglLahir,
            "berat_badan" to beratBadan,
            "tinggi_badan" to tinggiBadan
        )

//        Log.d("USER DATA", user.toString())
        db.collection("users").document(email)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(
                    baseContext,
                    "Update Profile Success!",
                    Toast.LENGTH_SHORT,
                ).show()
                startActivity(Intent(this,Profile::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(
                    baseContext,
                    "Update Profile Failed!",
                    Toast.LENGTH_SHORT,
                ).show()
            }

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