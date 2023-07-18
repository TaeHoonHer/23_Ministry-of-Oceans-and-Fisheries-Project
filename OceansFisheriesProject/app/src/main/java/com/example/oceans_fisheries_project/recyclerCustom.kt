package com.example.oceans_fisheries_project

import android.graphics.drawable.Drawable
import android.provider.ContactsContract.Data
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oceans_fisheries_project.databinding.ItemBinding

class recyclerCustom(
    var img :Drawable,
    var title: String,
    var date : String
)

class Custom(private val data: ArrayList<Data>): RecyclerView.Adapter<Custom.CustomViewHolder>(){
    private lateinit var binding: ItemBinding

    inner class CustomViewHolder(binding: ItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : recyclerCustom){
            binding.titletxt.text = item.title
            binding.datetxt.text = item.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {


        //각 아이템에 대한 설정 변경

    }


}