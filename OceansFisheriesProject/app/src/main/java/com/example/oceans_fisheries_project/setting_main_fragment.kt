package com.example.oceans_fisheries_project

import android.app.Activity
import android.content.SharedPreferences
import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.oceans_fisheries_project.databinding.SettingMainFragBinding

class setting_main_fragment :Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = SettingMainFragBinding.inflate(layoutInflater)
        binding.option1btn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frag,nickname_fragment())
                .addToBackStack(null)
                .commit()
        }

        binding.option2btn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frag,password_fragment())
                .addToBackStack(null)
                .commit()
        }

        binding.logoutLin.setOnClickListener { // 로그아웃
            val sharedPreferences = requireActivity().getSharedPreferences("shp",Activity.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear() // 로그아웃시 자동입력 정보 초기화
            editor.commit()
        }


        return binding.root
    }
}