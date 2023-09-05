package com.example.oceans_fisheries_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oceans_fisheries_project.databinding.ScrapFragmentBinding

class scrapFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ScrapFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }
}