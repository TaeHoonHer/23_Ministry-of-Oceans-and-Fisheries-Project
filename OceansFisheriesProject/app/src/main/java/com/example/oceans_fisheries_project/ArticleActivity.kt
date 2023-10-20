package com.example.oceans_fisheries_project

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.oceans_fisheries_project.databinding.ArticleFragmentBinding

class ArticleActivity: AppCompatActivity() {    //기사 본문 엑티비티

    lateinit var binding: ArticleFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ArticleFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startArticle()


    }

    fun startArticle(){  // 뉴스 기사 바인딩 함수
        var title = intent.getStringExtra("title")  //리사이클러 뷰로부터 현재 선택된 기사의 정보를 가져온다
        var date = intent.getStringExtra("date")
        var img = intent.getStringExtra("img_href")
        var content = intent.getStringExtra("content")
        binding.titletxt.text = title
        binding.datetxt.text = date
        binding.contenttxt.text = content

        Glide.with(this)    //기사 본문 이미지
            .load(img)
            .into(binding.newsImg)

    }
}