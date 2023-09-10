package com.example.oceans_fisheries_project

import android.os.Bundle
import android.provider.Settings.Global
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class fragmentMain : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        var arr = arrayListOf<recyclerCustom>()

        var str = "popular"  // 초기 popular 로 화면 지정
        GlobalScope.launch {
            binding.btxt.text = clickTop(str)
        }

        binding.potxt.setOnClickListener {  // 버튼 클릭에따른 메인 박스 텍스트 변경
            var str = "popular"
            GlobalScope.launch {
                binding.btxt.text = clickTop(str)
            }
        }
        binding.lotxt.setOnClickListener {
            var str = "logistics"
            GlobalScope.launch {
                binding.btxt.text = clickTop(str)
            }
        }
        binding.retxt.setOnClickListener {
            var str = "recent"
            GlobalScope.launch {
                binding.btxt.text = clickTop(str)
            }
        }

        var databaseReference = FirebaseDatabase.getInstance().getReference("news")
        databaseReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(childSnapshot in snapshot.children){
                    var title = childSnapshot.child("title").getValue(String::class.java)
                    var date = childSnapshot.child("date").getValue(String::class.java)
                    var img = childSnapshot.child("img_href").getValue(String::class.java)
                    var content = childSnapshot.child("content").getValue(String::class.java)

                    var data = recyclerCustom(img!!,title!!,date!!,content!!)
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

    suspend fun clickTop(str : String): String{  // data 변경 메소드
        return suspendCoroutine {continuation ->
            val databaseReference1 = FirebaseDatabase.getInstance().getReference(str)
            databaseReference1.addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var ret = ""
                    for(ch in snapshot.children){
                        var title = ch.child("title").getValue(String::class.java)
                        var date = ch.child("date").getValue(String::class.java)
                        var img = ch.child("img_href").getValue(String::class.java)
                        var content = ch.child("content").getValue(String::class.java)

                        ret = title!!
                    }
                    continuation.resume(ret)
                }
                override fun onCancelled(error: DatabaseError) {
                    println(error)

                    continuation.resume("")
                }
            })
        }
    }
}

