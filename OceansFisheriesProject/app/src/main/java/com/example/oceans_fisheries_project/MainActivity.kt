package com.example.oceans_fisheries_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.activity.OnBackPressedCallback
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
            .replace(R.id.frag, fragmentMain()).addToBackStack(null)
            .commit()

        binding.bottomnav.setOnItemSelectedListener { item ->  // 바텀 네비게이션 클릭 이벤트
            when (item.itemId) {
                R.id.home ->supportFragmentManager.beginTransaction()
                    .replace(R.id.frag, fragmentMain()).addToBackStack(null)
                    .commit()
                R.id.search ->supportFragmentManager.beginTransaction()
                    .replace(R.id.frag, SearchFragment()).addToBackStack(null)
                    .commit()
                R.id.mypage -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frag, mypageFragment()).addToBackStack(null)
                    .commit()
            }
            true
        }

        // 뒤로가기 버튼 이벤트 처리
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val fragmentManager = supportFragmentManager

                // 현재 보여지는 프래그먼트를 스택에서 제거
                if (fragmentManager.backStackEntryCount > 1) {
                    fragmentManager.popBackStack()
                }
                else {
                    // 백 스택에 아무것도 없으면 액티비티를 종료
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)


        binding.sidenave.setNavigationItemSelectedListener{menuitem->

            Log.d("qqq","사이드바 입력")
            when(menuitem.itemId){
                R.id.scronews ->  supportFragmentManager.beginTransaction()
                    .replace(R.id.frag, mypageFragment()).addToBackStack(null)
                    .commit()
            }

            binding.layoutDraw.closeDrawer(GravityCompat.START) // Drawer닫는다

            true
        }

    }


    fun toolbarClick(v:View){ //사이드바 오픈
        binding.layoutDraw.openDrawer(GravityCompat.START)
    }
}