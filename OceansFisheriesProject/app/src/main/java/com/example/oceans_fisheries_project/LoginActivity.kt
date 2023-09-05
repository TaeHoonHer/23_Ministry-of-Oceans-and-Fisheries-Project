package com.example.oceans_fisheries_project

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
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

        val sharedPreferences = getSharedPreferences("shp", MODE_PRIVATE)
        val logid = sharedPreferences.getString("id",null)
        val logpass = sharedPreferences.getString("pass",null)
        val logcheck = sharedPreferences.getBoolean("check",false)

        if(!logid.isNullOrEmpty()  && !logpass.isNullOrEmpty() &&logcheck != null){
            signIn(logid,logpass)
        }

        binding.signInBtn2.setOnClickListener {
            val email = binding.idEdittxt.text.toString()
            val pass = binding.passEdittxt.text.toString()
            signIn(email,pass)
        }
    }

    fun signIn(email: String, pass : String){
        uasuth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()

                    val sharedPreferences = getSharedPreferences("shp",Activity.MODE_PRIVATE)
                    var autoLogin =sharedPreferences.edit()  // 자동 로그인 입력
                    autoLogin.putString("id",email)
                    autoLogin.putString("pass",pass)
                    autoLogin.putBoolean("check", binding.stayCeck.isChecked)
                    autoLogin.commit()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"로그인 오류",Toast.LENGTH_SHORT).show()
                }

            }
    }
}