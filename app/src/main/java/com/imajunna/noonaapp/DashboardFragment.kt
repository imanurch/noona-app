package com.imajunna.noonaapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imajunna.noonaapp.databinding.FragmentDashboardBinding
import com.imajunna.noonaapp.model.JournalModel
import com.imajunna.noonaapp.ui.FactOrMythActivity
import com.imajunna.noonaapp.ui.Profile

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentDashboardBinding
    private  lateinit var db:FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root

        db = Firebase.firestore

        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_profileFragment)
        }

        binding.btnLessons.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_lessonsFragment2)
        }

        binding.btnCalendar.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_journalFragment2)
        }

        binding.btnFactOrMyth.setOnClickListener {
            val intent = Intent(activity, FactOrMythActivity::class.java)
            startActivity(intent)
        }

        var userData = getUserData()
        binding.textWelcoming.text = "Welcome, ${userData["nama"]}!"

        getHistoryJournalFromFirebase()

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getUserData(): Map<String, String> {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("localData", Context.MODE_PRIVATE)

        val gson = Gson()
        val json = sharedPreferences.getString("userData", "")

        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(json, type) ?: emptyMap()
    }

    fun getHistoryJournalFromFirebase(){
        var historyJournal = ArrayList<JournalModel>()

        //GET USER EMAIL FROM AUTH
        val userEmail = FirebaseAuth.getInstance().currentUser?.email

        val docRef = db.collection("journals").whereEqualTo("email", userEmail)
        docRef.get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot != null) {
                    for (document in querySnapshot.documents) {
                        historyJournal.add(JournalModel.fromMap(document.data!!))
                    }
                    Log.d("DATA1", historyJournal.toString())

                    saveHistoryJournal(historyJournal)
                }
            }
    }

    fun saveHistoryJournal(data:ArrayList<JournalModel>){
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("localData", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()

        val gson = Gson()
        val json = gson.toJson(data)

        editor.putString("historyJournal", json)
        editor.apply()
    }


}