package com.example.oceans_fisheries_project

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.oceans_fisheries_project.databinding.ChangePasswordFragmentBinding
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class password_fragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = ChangePasswordFragmentBinding.inflate(layoutInflater)

        val auth = FirebaseAuth.getInstance()
        var user = auth.currentUser
        val email = user?.email

        binding.okBtn.setOnClickListener {  //비밀번호 변경이벤트
            val newpass = binding.newpas1Edi.text.toString()
            val newpassok = binding.newpass2Edi.text.toString()

            val credential = EmailAuthProvider.getCredential(email!!,binding.currentpassEdi.text.toString())  // 이메일, password로 사용자 재인증
            user?.reauthenticate(credential)  // 인증 성공시
                ?.addOnCompleteListener {
                    if(it.isSuccessful){
                        Log.d("q","사용자 인증성공")

                        if(newpass == newpassok){  // 비밀번호 비교
                                user.updatePassword(newpassok).addOnCompleteListener {//비벌번호 변경
                                if(it.isSuccessful){
                                    Toast.makeText(requireContext(),"비밀번호 변경",Toast.LENGTH_SHORT).show()
                                    Log.d("qwqwqw","비밀번호 변경성공")

                                    val sharedPreferences = requireActivity().getSharedPreferences("shp",Activity.MODE_PRIVATE)  //로그인 체크박스 false
                                    val editor = sharedPreferences.edit()
                                    editor.clear()
                                    editor.commit()
                                }
                                else{
                                    Log.d("qwqwqw", "${it.exception}")
                                }

                            }


                        }
                        else{
                            Toast.makeText(requireContext(),"비밀번호 불일치",Toast.LENGTH_SHORT).show()
                            Log.d("qwqwqw","비밀번호 변경실패")
                        }
                    }
                    else{
                        Log.d("q","사용자 인증실패")
                        Toast.makeText(requireContext(),"현재 비밀번호가 다릅니다",Toast.LENGTH_SHORT).show()
                    }
                }
        }
        return binding.root
    }
}