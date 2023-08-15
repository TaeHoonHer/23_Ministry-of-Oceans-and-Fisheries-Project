package com.example.oceans_fisheries_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oceans_fisheries_project.databinding.MainFragmentBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class fragmentMain : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        var arr = arrayListOf<recyclerCustom>()

        binding.potxt.setOnClickListener {
            binding.btxt.text = "EU Fisheries Ministers decline to cease destructive fishing practices"
        }
        binding.lotxt.setOnClickListener {
            binding.btxt.text = "Gas and condensate discoveries to be developed in the Norwegian Sea"
        }
        binding.retxt.setOnClickListener {
            binding.btxt.text = "World’s first pure battery tanker made her first bunkering operation"
        }





        var databaseReference = FirebaseDatabase.getInstance().getReference("news")
        databaseReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(childSnapshot in snapshot.children){
                    var title = childSnapshot.child("title").getValue(String::class.java)
                    var date = childSnapshot.child("date").getValue(String::class.java)

                    var data = recyclerCustom(R.drawable.mypage,title!!,date!!)
                    arr.add(data)
                }

                var adap = Custom(arr)
                binding.recy.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    setHasFixedSize(true)
                    adapter = adap
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error)
            }

        })
        return binding.root
        }
}