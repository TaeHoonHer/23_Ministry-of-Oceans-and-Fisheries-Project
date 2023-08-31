package com.example.oceans_fisheries_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Toast
import com.example.oceans_fisheries_project.databinding.ActivityLoginBinding
import com.example.oceans_fisheries_project.databinding.ActivityStartBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val uasuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInBtn2.setOnClickListener {
            val email = binding.idEdittxt.text.toString()
            val pass = binding.passEdittxt.text.toString()
            signIn(email,pass)
        }
    }

    fun signIn(email: String, pass : String){
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        Log.d("name","${user?.displayName}")

        uasuth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"로그인 오류",Toast.LENGTH_SHORT).show()
                }

            }
    }
}