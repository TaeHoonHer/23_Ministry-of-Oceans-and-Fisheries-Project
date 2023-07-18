package com.example.oceans_fisheries_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.oceans_fisheries_project.databinding.ActivityLoginBinding
import com.example.oceans_fisheries_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var viewList = ArrayList<View>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        viewList.add(layoutInflater.inflate(R.layout.activity_mypage,null))
//
//        binding.bottomnav.setOnNavigationItemReselectedListener {
//            when(it.itemId){
//                R.id.mypage->supportFragmentManager.beginTransaction().replace(R.id.mainactivity,).commit()
//                //R.id.home ->
//            }
//            return@setOnNavigationItemReselectedListener
//        }
    }


}