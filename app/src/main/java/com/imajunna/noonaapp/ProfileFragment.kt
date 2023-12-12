package com.imajunna.noonaapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.imajunna.noonaapp.databinding.FragmentProfileBinding
import com.imajunna.noonaapp.ui.LoginPage
import com.imajunna.noonaapp.ui.ProfileEdit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
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

    private lateinit var binding:FragmentProfileBinding
    private lateinit var db: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        db = Firebase.firestore

        binding.btnLogout.setOnClickListener {
            signout()
            val intent = Intent(activity, LoginPage::class.java)
            startActivity(intent)
        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(activity, ProfileEdit::class.java)
            startActivity(intent)
        }

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

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun signout() {
        Firebase.auth.signOut()
    }

    private fun saveUserData(data: String) {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("localData", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString("userData", data)
        editor.apply()
    }
}