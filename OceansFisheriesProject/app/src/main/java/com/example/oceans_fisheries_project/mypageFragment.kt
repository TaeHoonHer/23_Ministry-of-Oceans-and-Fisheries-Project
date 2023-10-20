package com.example.oceans_fisheries_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oceans_fisheries_project.databinding.ActivityMypageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.FirebaseDatabaseKtxRegistrar
import com.google.firebase.ktx.Firebase

class mypageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ActivityMypageBinding.inflate(inflater,container,false)

        var arr = arrayListOf<recyclerCustom>()

        binding.settingbtn.setOnClickListener { // 세팅창으로 이동
            parentFragmentManager.beginTransaction().replace(R.id.frag,setting_main_fragment()).addToBackStack(null).commit() // 설정 프래그먼트로 이동
        }

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if(currentUser != null){
            val userId = currentUser.uid
            binding.nametxt.text = currentUser.displayName

            var databaseReference = FirebaseDatabase.getInstance().getReference("scrap").child(userId) // 파이어베이스 데이터베이스 불러온다


            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(childSnapshot in snapshot.children){
                        var title = childSnapshot.child("title").getValue(String::class.java)
                        var date = childSnapshot.child("date").getValue(String::class.java)
                        var img = childSnapshot.child("img_href").getValue(String::class.java)
                        var content = childSnapshot.child("content").getValue(String::class.java)
                        //bool 값 추가해서 data에 넣기
                        var data = recyclerCustom(img!!,title!!,date!!,content!!,false)
                        arr.add(data)
                    }

                    var adpater = Custom(arr)
                    binding.mypageRcyc.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = adpater
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    println(error)
                }
            })
        }
        return binding.root
    }

}