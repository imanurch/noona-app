package com.imajunna.noonaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityLoginPageBinding
import com.imajunna.noonaapp.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Initialize Firebase Firestore
        db = Firebase.firestore

        binding.btnMasuk.setOnClickListener{
            startActivity(Intent(this,LoginPage::class.java))
            finish()
        }

        binding.btnDaftar.setOnClickListener{
            //GET EDIT TEXT VALUE
            var nama = binding.etNama.text.toString()
            var email = binding.etEmail.text.toString()
            var password = binding.etSandi.text.toString()
            var cPassword = binding.etSandiKonfirmasi.text.toString()

            signup(nama, email, password, cPassword)
        }



    }

    fun signup(nama: String, email: String, password: String, cPassword: String) {
        val user = hashMapOf(
            "nama" to nama,
            "email" to email,
            "tgl_lahir" to "",
            "berat_badan" to "",
            "tinggi_badan" to ""
        )

        if (nama != "" && email != "" && password != "" && cPassword != "") {
           auth.createUserWithEmailAndPassword(email, password)
               .addOnCompleteListener(this) { task ->
                   if (task.isSuccessful) {
                       db.collection("users").document(email)
                           .set(user)
                           .addOnSuccessListener {
                               Firebase.auth.signOut()
                               Toast.makeText(
                                   baseContext,
                                   "Signup Success!",
                                   Toast.LENGTH_SHORT,
                               ).show()
                                startActivity(Intent(this,LoginPage::class.java))
                                finish()
                           }
                           .addOnFailureListener {
                               Toast.makeText(
                                   baseContext,
                                   "Signup Failed!",
                                   Toast.LENGTH_SHORT,
                               ).show()
                           }
                   } else {
                       Toast.makeText(
                           baseContext,
                           "Signup Failed!",
                           Toast.LENGTH_SHORT,
                       ).show()
                   }
               }
       }
    }
}