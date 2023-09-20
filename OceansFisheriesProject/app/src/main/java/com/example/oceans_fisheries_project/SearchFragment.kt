package com.example.oceans_fisheries_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import com.example.oceans_fisheries_project.databinding.SearchFragmentBinding
import kotlin.math.log

class SearchFragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SearchFragmentBinding.inflate(inflater,container,false)

        binding.searchbar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{  // 검색창 제출 버튼 누르면 인텐트 이동
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("mes","제출")
                val intent = Intent(requireContext(), SearchActivity::class.java)
                intent.putExtra("search","${query.toString()}")
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("mes","변경")
                return true
            }


        })


        return binding.root
    }
}