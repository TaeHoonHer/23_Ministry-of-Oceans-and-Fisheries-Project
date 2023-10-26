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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oceans_fisheries_project.databinding.SearchFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.log

class SearchFragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SearchFragmentBinding.inflate(inflater,container,false)

        val arr = arrayListOf<user>()

        binding.searchbar.setOnClickListener { // 검색창 버튼 누르면 인텐트 이동
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }


        val user = FirebaseAuth.getInstance().currentUser!!.uid
        val database = FirebaseDatabase.getInstance().getReference("usersearch").child(user)

        database.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children){
                    var title = child.child("title").getValue(String::class.java)

                    val data = user(title!!)
                    arr.add(data)
                }

                val adap = SearchRecy(arr)
                binding.recentrcy.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = adap
                    setHasFixedSize(true)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("tt",error.message)
            }

        })




        return binding.root
    }
}