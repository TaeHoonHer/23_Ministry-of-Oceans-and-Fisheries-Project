package com.example.oceans_fisheries_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.core.view.GravityCompat
import com.example.oceans_fisheries_project.databinding.ActivityLoginBinding
import com.example.oceans_fisheries_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager.beginTransaction()  // 초기 프래그먼트 설정
            .replace(R.id.frag, fragmentMain())
            .commit()

        binding.bottomnav.setOnItemSelectedListener { item ->  // 바텀 네비게이션 클릭 이벤트
            when (item.itemId) {
                R.id.home ->supportFragmentManager.beginTransaction()
                    .replace(R.id.frag, fragmentMain())
                    .commit()
                R.id.mypage -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frag, mypageFragment())
                    .commit()
            }
            true
        }
    }

    fun toolbarClick(v:View){ //사이드바 오픈
        binding.layoutDraw.openDrawer(GravityCompat.START)
    }
}