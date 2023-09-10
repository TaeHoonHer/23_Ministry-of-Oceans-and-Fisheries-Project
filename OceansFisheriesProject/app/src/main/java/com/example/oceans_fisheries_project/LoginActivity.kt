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
        val savecheck = sharedPreferences.getBoolean("savedId",false)

        binding.stayCeck.isChecked = logcheck
        binding.saveCeck.isChecked = savecheck

        if(!logid.isNullOrEmpty()  && !logpass.isNullOrEmpty() &&logcheck == true){  // 자동로그인 체크 true일경우 자동로그인
            signIn(logid,logpass)
        }
        if(!logid.isNullOrEmpty() && savecheck == true){ // 아이디 저장 true시 아이디 저장
            binding.idEdittxt.setText(logid)
        }

<<<<<<< HEAD
        binding.signInBtn2.setOnClickListener { // 로그인 
=======
<<<<<<< Updated upstream
        binding.signInBtn2.setOnClickListener {
=======
        binding.signInBtn2.setOnClickListener { // 로그인
>>>>>>> Stashed changes
>>>>>>> main
            val email = binding.idEdittxt.text.toString()
            val pass = binding.passEdittxt.text.toString()
            signIn(email,pass)
        }
        binding.signUptxt.setOnClickListener { // 회원가입창 이동
            var intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
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
                    autoLogin.putBoolean("savedId",binding.saveCeck.isChecked)
                    Log.d("test","${binding.stayCeck.isChecked}")
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