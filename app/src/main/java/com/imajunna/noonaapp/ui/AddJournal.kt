package com.imajunna.noonaapp.ui

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.imajunna.noonaapp.JournalFragment
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityAddJournalBinding
import com.imajunna.noonaapp.databinding.ActivityLoginPageBinding
import com.imajunna.noonaapp.databinding.ActivityPeriodJournalBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddJournal : AppCompatActivity() {
    private lateinit var binding: ActivityAddJournalBinding
    private lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db=Firebase.firestore

        binding.btnBack.setOnClickListener{
            startActivity(Intent(this,Dashboard::class.java))
            finish()
        }



        binding.btnSaveJournal.setOnClickListener {
            //HARI
            var hari = binding.etDate.text.toString()

            //MENS
            var mens = binding.swMens.isChecked.toString()

            //MOOD
            var moodRadioID = binding.rgMood.checkedRadioButtonId
            var moodRadio:RadioButton=findViewById(moodRadioID)
            var moodValue = moodRadio.tag.toString()

            //CAIRAN
            var cairanRadioID = binding.rgCairan.checkedRadioButtonId
            var cairanRadio: RadioButton = findViewById(cairanRadioID)
            var cairanValue = cairanRadio.text.toString()

            //ALIRAN
            var aliranRadioID = binding.rgAliran.checkedRadioButtonId
            var aliranRadio:RadioButton = findViewById(aliranRadioID)
            var aliranValue = aliranRadio.text.toString()

            //GEJALA
            var gejalaRadioID = binding.rgGejala.checkedRadioButtonId
            var gejalaRadio: RadioButton = findViewById(gejalaRadioID)
            var gejalaValue = gejalaRadio.text.toString()

            //SUHU
            var suhu = binding.etSuhu.text.toString()

            //MINUM
            var minum = binding.etMinum.text.toString()

            //OLAHRAGA
            var olahragaRadioID = binding.rgOlahraga.checkedRadioButtonId
            var olahragaRadio: RadioButton = findViewById(olahragaRadioID)
            var olahragaValue = olahragaRadio.text.toString()

            Log.d("JOURNAL VALUE", "${hari} | ${mens} | ${moodValue} | ${cairanValue} | ${aliranValue} | ${gejalaValue} | ${suhu} | ${minum} | ${olahragaValue}")
            sendJournal(hari,mens,moodValue,cairanValue,aliranValue,gejalaValue,suhu,minum,olahragaValue)
        }

    }
    fun sendJournal(hari:String, mens:String, mood:String, cairan:String, aliran:String, gejala:String, suhu:String, minum:String, olahraga:String){
        //GET USER EMAIL FROM AUTH
        val userEmail = FirebaseAuth.getInstance().currentUser?.email

        var journalData = hashMapOf(
            "email" to userEmail,
            "hari" to hari,
            "mens" to mens,
            "mood" to mood,
            "cairan" to cairan,
            "aliran" to aliran,
            "gejala" to gejala,
            "suhu" to suhu,
            "minum" to minum,
            "olahraga" to olahraga
        )

        //DATE TIME
        var currDate = Date()
        var dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        var formattedDate = dateFormat.format(currDate)

        //CREATE ID JOURNAL DOC
        var docID = userEmail+formattedDate

        db.collection("journals").document(docID)
            .set(journalData)
            .addOnSuccessListener {
                Toast.makeText(
                    baseContext,
                    "Add Period Journal Success!",
                    Toast.LENGTH_SHORT,
                ).show()
                startActivity(Intent(this,Dashboard::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(
                    baseContext,
                    "Add Period Journal Failed!",
                    Toast.LENGTH_SHORT,
                ).show()
            }
    }

}