package com.example.oceans_fisheries_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oceans_fisheries_project.databinding.ActivityMypageBinding
import com.example.oceans_fisheries_project.databinding.MainFragmentBinding

class fragmentMain : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}