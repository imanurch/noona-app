package com.imajunna.noonaapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imajunna.noonaapp.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private lateinit var binding:ActivityLoginPageBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            binding = ActivityLoginPageBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Initialize Firebase Auth
            auth = Firebase.auth
            db = Firebase.firestore

            binding.btnDaftar.setOnClickListener{
                startActivity(Intent(this,SignUp::class.java))
                finish()
            }

            binding.btnLogin.setOnClickListener {
                var email = binding.etEmail.text.toString()
                var password = binding.etSandi.text.toString()

                signin(email, password)
            }
    }

    private fun signin(email: String, password: String) {
        if (email != "" && password != "") {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val docRef = db.collection("users").document(email)
                        docRef.get()
                            .addOnSuccessListener { document ->
                                if (document != null) {
                                    var data = document.data

                                    //convert to string using gson
                                    val gson = Gson()
                                    val jsonData = gson.toJson(data)

                                    //save to local
                                    saveUserData(jsonData)

                                    Toast.makeText(
                                        baseContext,
                                        "Signin success.",
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                    startActivity(Intent(this, Dashboard::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(
                                        baseContext,
                                        "Sign in failed!😓",
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                }
                            }
                            .addOnFailureListener { exception ->
                                Toast.makeText(
                                    baseContext,
                                    "Signin failed.",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Signin failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }

    private fun saveUserData(data: String) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("localData", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString("userData", data)
        editor.apply()
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