package com.example.oceans_fisheries_project

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
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.setting_fragctv,nickname_fragment())
                .addToBackStack(null)
                .commit()
        }

        binding.option2btn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.setting_fragctv,password_fragment())
                .addToBackStack(null)
                .commit()
        }


        return binding.root
    }
}