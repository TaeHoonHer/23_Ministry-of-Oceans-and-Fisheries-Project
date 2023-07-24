package com.example.oceans_fisheries_project

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.oceans_fisheries_project.databinding.ActivityMypageAditBinding


class mypage_ediAcitivity : AppCompatActivity() {

    lateinit var binding: ActivityMypageAditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMypageAditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.setting_fragctv,setting_main_fragment())
            .commit()








    }

    fun toolbarClick(v: View){
        binding.layoutDraw.openDrawer(GravityCompat.START)
    }


}