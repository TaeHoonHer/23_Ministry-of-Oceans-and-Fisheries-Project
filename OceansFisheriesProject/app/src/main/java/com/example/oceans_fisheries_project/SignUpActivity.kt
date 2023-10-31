package com.example.oceans_fisheries_project

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oceans_fisheries_project.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
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
            val name = binding.signNameEdittxt.text.toString()

            if(binding.signNameEdittxt.text.isEmpty()) { //이름 없을시
                Toast.makeText(this, "이름을 입력하세요", Toast.LENGTH_SHORT).show()
            } else if (!binding.signpassCheckEdittxt.text.toString().equals(password)) { //비밀번호 다를시
                Toast.makeText(this, "비밀번호를 확인해주세요 6자리 이상!", Toast.LENGTH_SHORT).show()
            } else {
                signup(email, password,name)  // 이름 추가
            }

        }
    }

    fun signup(email :String, password : String, name:String){ //회원가입
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    // 사용자 닉네임 프로필 업데이트
                    val userProfile = UserProfileChangeRequest.Builder()
                        .setDisplayName("${name}")
                        .build()
                    val user = auth.currentUser
                    user?.updateProfile(userProfile)
                        ?.addOnCompleteListener {
                            if(task.isSuccessful){
                                Log.d("qq","닉네임 지정성공")
                                user.reload()
                            }
                            else{
                                Log.d("qq","닉네임 지정실패")
                            }
                        }

                    Toast.makeText(this@SignUpActivity,"${user!!.displayName}",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@SignUpActivity,"회원가입 실패",Toast.LENGTH_SHORT).show()
                }
            }


    }

}