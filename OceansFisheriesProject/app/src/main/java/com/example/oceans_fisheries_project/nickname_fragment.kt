package com.example.oceans_fisheries_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.oceans_fisheries_project.databinding.ChangeNameFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class nickname_fragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = ChangeNameFragmentBinding.inflate(layoutInflater,container,false)

        var auth = FirebaseAuth.getInstance()
        var user = auth.currentUser

        binding.okBtn.setOnClickListener {  //닉네임 변경
            if(binding.nicknameEdi.text!=null){
                val userProFile = UserProfileChangeRequest.Builder()
                    .setDisplayName(binding.nicknameEdi.text.toString())
                    .build()
                user?.updateProfile(userProFile)
                    ?.addOnCompleteListener {task ->
                        if(task.isSuccessful){
                            user?.reload()
                            Toast.makeText(requireContext(),"닉네임이 변경되었습니다",Toast.LENGTH_SHORT).show()
                            Log.d("user","${user.displayName}")

                        }
                    }

            }
        }

        return binding.root
    }
}