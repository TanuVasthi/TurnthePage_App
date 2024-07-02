package com.example.turnthepage.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.R
import com.example.turnthepage.adapter.BuyAgainAdapter
import com.example.turnthepage.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var buyAgainAdapter: BuyAgainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHistoryBinding.inflate(layoutInflater,container,false)
        setupRecyclerView()
        return binding.root
    }
    private fun setupRecyclerView(){
        val buyAgainFoodName = arrayListOf("book 1","book 2","book 3")
        val buyAgainFoodPrice = arrayListOf("300","400","350")
        val buyAgainFoodImage = arrayListOf(R.drawable.prideandprejudice,R.drawable.greatgatsby,R.drawable.mobydick)
        buyAgainAdapter = BuyAgainAdapter(buyAgainFoodName,buyAgainFoodPrice,buyAgainFoodImage)
        binding.BuyAgainRecyclerView.adapter=buyAgainAdapter
        binding.BuyAgainRecyclerView.layoutManager=LinearLayoutManager(requireContext())
    }
    companion object {
    }
}
