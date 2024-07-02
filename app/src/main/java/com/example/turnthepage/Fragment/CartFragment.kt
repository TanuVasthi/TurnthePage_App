package com.example.turnthepage.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.CongratsBottomSheet
import com.example.turnthepage.PayOutActivity
import com.example.turnthepage.R
import com.example.turnthepage.adapter.CartAdapter
import com.example.turnthepage.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container,false)

        val cartBookName = listOf("Pride and Prejudice","Moby-Dick","Great Gatsby")
        val cartItemPrice = listOf("200","320","300")
        val cartImage =listOf(
            R.drawable.prideandprejudice,
            R.drawable.mobydick,
            R.drawable.greatgatsby
        )
        val adapter = CartAdapter(ArrayList(cartBookName), ArrayList(cartItemPrice), ArrayList(cartImage))

        binding.cartRecyclerBin.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerBin.adapter = adapter

        binding.proceedButton.setOnClickListener{
            val intent = Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    companion object {

    }
}