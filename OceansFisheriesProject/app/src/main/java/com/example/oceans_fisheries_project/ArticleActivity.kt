package com.example.oceans_fisheries_project

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oceans_fisheries_project.databinding.ArticleFragmentBinding

class ArticleActivity: AppCompatActivity() {

    lateinit var binding: ArticleFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ArticleFragmentBinding.inflate(layoutInflater)

        setContentView(binding.root)



    }
}