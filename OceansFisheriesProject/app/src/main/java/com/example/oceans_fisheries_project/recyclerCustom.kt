package com.example.oceans_fisheries_project

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.oceans_fisheries_project.databinding.ItemBinding

data class recyclerCustom(
    var img: Int,
    var title: String,
    var date: String
)

class Custom(private val data: ArrayList<recyclerCustom>): RecyclerView.Adapter<Custom.CustomViewHolder>(){
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
        val item = data[position]
        holder.bind(item)

        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 200
        holder.itemView.requestLayout()

        binding.root.setOnClickListener { //기사 페이지로 이동
            Toast.makeText(binding.root.context,"${item.title}",Toast.LENGTH_SHORT).show()
            var intent = Intent(binding.root.context,ArticleActivity::class.java)
            intent.putExtra("title","${item.title}")
            intent.putExtra("date","${item.date}")
            startActivity(binding.root.context,intent,null)
        }




        //각 아이템에 대한 설정 변경

    }


}

