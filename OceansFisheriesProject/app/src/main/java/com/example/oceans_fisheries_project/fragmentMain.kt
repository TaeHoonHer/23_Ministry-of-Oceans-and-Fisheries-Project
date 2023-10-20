package com.example.oceans_fisheries_project

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
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

        val animationView = binding.loadingAniview // 로딩 이미지 생성
        animationView.setAnimation("shiplaoding.json")
        animationView.loop(true)
        animationView.playAnimation()


        var str = "popular"  // 초기 popular 로 화면 지정
        GlobalScope.launch {
            binding.btxt.text = clickTop(str).split(" ")[0]
        }

        binding.potxt.setOnClickListener {  // 버튼 클릭에따른 메인 박스 텍스트 변경
            str = "popular"
            GlobalScope.launch {
                binding.btxt.text = clickTop(str).split(" ")[0] // clickTop 함수 리턴 1인덱스
            }
        }
        binding.lotxt.setOnClickListener {
            str = "logistics"
            GlobalScope.launch {
                binding.btxt.text = clickTop(str).split(" ")[0]
            }
        }
        binding.retxt.setOnClickListener {
            str = "recent"
            GlobalScope.launch {
                binding.btxt.text = clickTop(str).split(" ")[0]
            }
        }

        var databaseReference = FirebaseDatabase.getInstance().getReference("news") // db를 불러서 리사이클러 뷰로 적용
        databaseReference.addListenerForSingleValueEvent(object :ValueEventListener{    //데이터 필드에 isSelected가 없으면 추가 초기화 false
            override fun onDataChange(snapshot: DataSnapshot) {
                for(childSnapshot in snapshot.children){
                    if(!childSnapshot.child("isSelected").exists()){
                        childSnapshot.ref.child("isSelected").setValue(false)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error)
            }
        })
        databaseReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(childSnapshot in snapshot.children){
                    var title = childSnapshot.child("title").getValue(String::class.java)
                    var date = childSnapshot.child("date").getValue(String::class.java)
                    var img = childSnapshot.child("img_href").getValue(String::class.java)
                    var content = childSnapshot.child("content").getValue(String::class.java)
                    var selected = childSnapshot.child("isSelected").getValue(Boolean::class.java)
                    //bool 값 추가해서 data에 넣기
                    var data = recyclerCustom(img!!,title!!,date!!,content!!, selected!!)
                    arr.add(data)  //데이터 리스트 캡슐화
                }

                if(isAdded){ // 프래그먼트가 context에 추가 되었다면
                    var adap = Custom(arr)  // 리사이클러 뷰 어뎁터 설정, 적용
                    binding.recy.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        setHasFixedSize(true)
                        adapter = adap
                    }
                }

                animationView.visibility = View.GONE // 로딩 이미지 제거

            }

            override fun onCancelled(error: DatabaseError) {
                println(error)
            }
        })

        binding.img.setOnClickListener{     // 메인 상단 박스 클릭시 기사 페이지 이동
            var intent = Intent(requireContext(),ArticleActivity::class.java)

            GlobalScope.launch {
                var (title, date, img, content) = clickTop(str).split(" ")
                intent.putExtra("title","${title}")
                intent.putExtra("date","${date}")
                intent.putExtra("img_href","${img}")
                intent.putExtra("content","${content}")

                startActivity(intent)
            }
        }

        return binding.root
        }

    suspend fun clickTop(str : String): String{  //  상단 박스 data 변경 메소드
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

                        ret = "${title} ${date} ${img} ${content}"
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

