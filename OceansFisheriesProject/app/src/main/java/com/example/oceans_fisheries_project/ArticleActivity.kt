package com.example.oceans_fisheries_project

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.oceans_fisheries_project.databinding.ArticleFragmentBinding

class ArticleActivity: AppCompatActivity() {

    lateinit var binding: ArticleFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ArticleFragmentBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var title = intent.getStringExtra("title")
        var date = intent.getStringExtra("date")
        var img = intent.getStringExtra("img_href")
        var content = intent.getStringExtra("content")
        binding.titletxt.text = title
        binding.datetxt.text = date
        binding.contenttxt.text = content


        Glide.with(this)
            .load(img)
            .into(binding.newsImg)
    }
}