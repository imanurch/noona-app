package com.imajunna.noonaapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityLessonPageBinding
import com.imajunna.noonaapp.databinding.ActivityLessonsBinding
import com.imajunna.noonaapp.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this, ProfileEdit::class.java))
            finish()
        }
        binding.btnLogout.setOnClickListener {
            signout()
        }
        val bottomNavigationView =  findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController= findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)

        //GET USER EMAIL FROM AUTH
        val userEmail = FirebaseAuth.getInstance().currentUser?.email

        val docRef = db.collection("users").document(userEmail!!)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    var data = document.data

                    //convert to string using gson
                    val gson = Gson()
                    val jsonData = gson.toJson(data)

                    //save to local
                    saveUserData(jsonData)

                    var nama = data!!["nama"].toString()
                    var email = data!!["email"].toString()
                    var tglLahir = data!!["tgl_lahir"].toString()
                    var beratBadan = data!!["berat_badan"].toString()
                    var tinggiBadan = data!!["tinggi_badan"].toString()


                    if (nama != "") {
                        binding.textNama.text = nama
                    }
                    if (email != "") {
                        binding.textEmail.text = email
                    }
                    if (tglLahir != "") {
                        binding.textTglLahir.text = tglLahir
                    }
                    if (beratBadan != "") {
                        binding.textBeratBadan.text = beratBadan
                    }
                    if (tinggiBadan != "") {
                        binding.textTinggiBadan.text = tinggiBadan
                    }
                }
            }


    }

    private fun signout() {
        Firebase.auth.signOut()
        startActivity(Intent(this, LoginPage::class.java))
        finish()
    }

    private fun saveUserData(data: String) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("localData", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString("userData", data)
        editor.apply()
    }
}