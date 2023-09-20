package com.example.oceans_fisheries_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oceans_fisheries_project.databinding.SearchActivityBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchActivity : AppCompatActivity() {

    lateinit var binding : SearchActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SearchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var isFirstSearch = true

        var str = intent.getStringExtra("search")
        val defaultquery = str
        binding.searchbar2.setQuery(defaultquery , true)



        binding.searchbar2.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{ //제출버튼 클릭시
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("mmm","${query}")


                if (isFirstSearch) {
                    binding.searchbar2.isIconified = true
                    binding.searchbar2.clearFocus() // 검색 바에서 포커스 해제
                    isFirstSearch = false
                }

                Log.d("mmm","제출")
                val firebaseDatabase = FirebaseDatabase.getInstance()
                val database = firebaseDatabase.getReference("news")  //news에서 레퍼런스 가져온다
                val searchquery = database.orderByChild("title")       // 제목으로 정렬한 후 검색어로 시작하는 값을 가져온다
                    .startAt(query)

                searchquery.addListenerForSingleValueEvent(object : ValueEventListener{  //쿼리를 한번 순회하면서 스냅샷 정보를 어뎁터에 넣는다
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val arr = arrayListOf<recyclerCustom>()
                        for(sn in snapshot.children){
                            var title = sn.child("title").getValue(String::class.java)
                            var date = sn.child("date").getValue(String::class.java)
                            var img = sn.child("img_href").getValue(String::class.java)
                            var content = sn.child("content").getValue(String::class.java)

                            var data = recyclerCustom(img!!,title!!,date!!,content!!)
                            arr.add(data)
                        }

                        val adap = Custom(arr)
                        binding.recySearchacti.apply {
                            layoutManager = LinearLayoutManager(this@SearchActivity)
                            setHasFixedSize(true)
                            adapter = adap
                            Log.d("mmm","어뎁터 추가")
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.d("mmm","오류")
                    }

                })

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


    }
}