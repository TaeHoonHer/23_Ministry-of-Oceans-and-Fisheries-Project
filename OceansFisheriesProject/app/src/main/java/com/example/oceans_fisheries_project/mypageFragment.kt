package com.example.oceans_fisheries_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oceans_fisheries_project.databinding.ActivityMypageBinding

class mypageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ActivityMypageBinding.inflate(inflater,container,false)

        binding.settingbtn.setOnClickListener {
            var intent = Intent(activity,mypage_ediAcitivity::class.java)
            startActivity(intent)

        }

        return binding.root



    }

}