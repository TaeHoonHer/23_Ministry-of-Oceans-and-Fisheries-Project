package com.example.oceans_fisheries_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oceans_fisheries_project.databinding.MainFragmentBinding

class fragmentMain : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = MainFragmentBinding.inflate(inflater, container, false)


            var arr = arrayListOf( // 임시 데이터
                recyclerCustom(
                    img = R.drawable.bac,
                    title = "a",
                    date = "1"
                ),
                recyclerCustom(
                    img = R.drawable.bac,
                    title = "b",
                    date = "2"
                )
            )

            var adap = Custom(arr)
            binding.recy.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = adap
            }

        return binding.root

        }

}