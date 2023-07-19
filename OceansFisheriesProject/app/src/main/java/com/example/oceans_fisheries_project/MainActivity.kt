package com.example.oceans_fisheries_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.oceans_fisheries_project.databinding.ActivityLoginBinding
import com.example.oceans_fisheries_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frag, fragmentMain())
            .commit()



        binding.bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home ->supportFragmentManager.beginTransaction()
                    .replace(R.id.frag, fragmentMain())
                    .commit()

                R.id.mypage -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frag, mypageFragment()).commit()
            }
            true
        }
    }


}