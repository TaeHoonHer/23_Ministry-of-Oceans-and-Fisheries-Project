package com.example.oceans_fisheries_project

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oceans_fisheries_project.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity: AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)

        setContentView(binding.root)

        auth =Firebase.auth

        binding.signInBtn2.setOnClickListener {
            val email = binding.signidEdittxt.text.toString()
            val password = binding.signpassEdittxt.text.toString()

            if(binding.signNameEdittxt.text.isEmpty()) { //이름 없을시
                Toast.makeText(this, "이름을 입력하세요", Toast.LENGTH_SHORT).show()
            } else if (!binding.signpassCheckEdittxt.text.toString().equals(password)) { //비밀번호 다를시
                Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                signup(email, password)
            }

        }
    }

    fun signup(email :String, password : String){ //회원가입
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    Toast.makeText(this@SignUpActivity,"회원가입 성공",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@SignUpActivity,"회원가입 실패",Toast.LENGTH_SHORT).show()
                }
            }


    }

}