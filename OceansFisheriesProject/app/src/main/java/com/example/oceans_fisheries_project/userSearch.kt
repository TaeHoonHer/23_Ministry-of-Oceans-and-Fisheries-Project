package com.example.oceans_fisheries_project

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.oceans_fisheries_project.databinding.RecentsearchBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.ThreadInitializer

data class user(
    var title : String
)

class SearchRecy(private val data : ArrayList<user>) : RecyclerView.Adapter<SearchRecy.customSearch>(){
    private lateinit var binding : RecentsearchBinding

    inner class customSearch(binding : RecentsearchBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : user){
            binding.usersearchtxt.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customSearch {
        binding = RecentsearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return customSearch(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: customSearch, position: Int) {
        val item = data[position]
        holder.bind(item)

        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 130
        holder.itemView.requestLayout()

        val user = FirebaseAuth.getInstance().currentUser!!.uid
        val database = FirebaseDatabase.getInstance().getReference("usersearch").child(user)
        val query = database.orderByChild("title").equalTo(item.title) // title로 정렬한후 현재 bindgind된 item의 제목과 같은 값을 반환

        binding.del.setOnClickListener {
            query.addListenerForSingleValueEvent(object :ValueEventListener{ // 쿼리 조건에 맞는 데이터를 순회한다
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(sn in snapshot.children){
                            sn.ref.removeValue()

                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    println(error.message)
                }

            })
        }



    }

}