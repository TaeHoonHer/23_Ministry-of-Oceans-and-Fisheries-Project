package com.example.oceans_fisheries_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oceans_fisheries_project.databinding.ActivityStartBinding

class FirstActivity : AppCompatActivity() {

    lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signInBtn.setOnClickListener {  // 로그인 버튼
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signUpBtn.setOnClickListener {  // 회원가입 버튼
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}