package com.example.turnthepage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.turnthepage.databinding.BuyAgainItemBinding

class BuyAgainAdapter(private val BuyAgainBookName:ArrayList<String>, private val BuyAgainBookPrice:ArrayList<String>, private val BuyAgainBookImage:ArrayList<Int>):
    RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {
    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        holder.bind(
            BuyAgainBookName[position],
            BuyAgainBookPrice[position],
            BuyAgainBookImage[position]
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {
        val binding =
            BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyAgainViewHolder(binding)
    }

    override fun getItemCount(): Int = BuyAgainBookName.size
    class BuyAgainViewHolder(private val binding: BuyAgainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookName: String, bookPrice: String, bookImage: Int) {
            binding.buyAgainBookName.text = bookName
            binding.buyAgainBookPrice.text = bookPrice
            binding.buyAgainBookImage.setImageResource(bookImage)
        }

    }
}
