package com.example.oceans_fisheries_project

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.oceans_fisheries_project.databinding.ItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

data class recyclerCustom(
    var img: Int,
    var title: String,
    var date: String
)
class Custom(private val data: ArrayList<recyclerCustom>): RecyclerView.Adapter<Custom.CustomViewHolder>(){
    private lateinit var binding: ItemBinding

    var btnclickIndex = 0

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

        binding.root.setOnClickListener { //기사 페이지로 이동 // 아이템뷰 나타낸다
            Toast.makeText(binding.root.context,"${item.title}",Toast.LENGTH_SHORT).show()
            var intent = Intent(binding.root.context,ArticleActivity::class.java)
            intent.putExtra("title","${item.title}")
            intent.putExtra("date","${item.date}")
            startActivity(binding.root.context,intent,null)
        }
        binding.bookmarkbtn.setOnClickListener{
            addToDatabases(item)
        }
    }
    fun addToDatabases(item: recyclerCustom){
        val bookmark = FirebaseDatabase.getInstance().getReference("scrap")
        val bookmarkData = hashMapOf(
            "title" to item.title,
            "date" to item.date
        )
        val database = FirebaseDatabase.getInstance()
        val bookmarksRef = database.getReference("scrap")
        val query = bookmarksRef.orderByChild("title").equalTo(item.title)
        query.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(snapshot in snapshot.children){
                        snapshot.ref.removeValue()
                    }
                    println("데이터 삭제 성공")
                }
                else{
                    bookmark.push().setValue(bookmarkData)
                        .addOnSuccessListener {
                            println("데이터 저장 성공")
                        }
                        .addOnFailureListener { e->
                            println(e.message)
                        }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }
        })
    }
}

