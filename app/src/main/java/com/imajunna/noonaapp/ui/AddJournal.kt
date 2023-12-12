package com.imajunna.noonaapp.ui

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.databinding.ActivityAddJournalBinding
import com.imajunna.noonaapp.databinding.ActivityLoginPageBinding
import com.imajunna.noonaapp.databinding.ActivityPeriodJournalBinding

class AddJournal : AppCompatActivity() {
    private lateinit var binding: ActivityAddJournalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            startActivity(Intent(this,PeriodJournal::class.java))
            finish()
        }

        binding.rgMood.setOnCheckedChangeListener { radioGroup, checkedId ->
            val selectedMood: RadioButton = findViewById(checkedId)
            val rbMood0: RadioButton = findViewById(R.id.rb_mood_0)
            val rbMood1: RadioButton = findViewById(R.id.rb_mood_1)
            val rbMood2: RadioButton = findViewById(R.id.rb_mood_2)
            val rbMood3: RadioButton = findViewById(R.id.rb_mood_3)
            val rbMood4: RadioButton = findViewById(R.id.rb_mood_4)

//            Log.d("RB MOOD", checkedId.toString())

            selectedMood.backgroundTintMode = null

            if (checkedId != 2131362398) {
                rbMood0.backgroundTintMode = PorterDuff.Mode.MULTIPLY
            }
            if (checkedId != 2131362399) {
                rbMood1.backgroundTintMode = PorterDuff.Mode.MULTIPLY
            }
            if (checkedId != 2131362400) {
                rbMood2.backgroundTintMode = PorterDuff.Mode.MULTIPLY
            }
            if (checkedId != 2131362401) {
                rbMood3.backgroundTintMode = PorterDuff.Mode.MULTIPLY
            }
            if (checkedId != 2131362402) {
                rbMood4.backgroundTintMode = PorterDuff.Mode.MULTIPLY
            }

            binding.btnSaveJournal.setOnClickListener {
                var hari = binding.etDate.text.toString()
                var mens = binding.swMens.isChecked.toString()
                var mood = binding.rgMood.checkedRadioButtonId.toString()
                var cairan = binding.rgCairan.checkedRadioButtonId.toString()
                var aliran = binding.rgAliran.checkedRadioButtonId.toString()
                var gejala = binding.rgGejala.checkedRadioButtonId.toString()
                var suhu = binding.etSuhu.text.toString()
                var minum = binding.etMinum.text.toString()
                var olahraga = binding.rgOlahraga.checkedRadioButtonId.toString()

                Log.d("JOURNAL VALUE", "${hari} | ${mens} | ${mood} | ${cairan} | ${aliran} | ${gejala} | ${suhu} | ${minum} | ${olahraga}")
            }
        }




    }
}