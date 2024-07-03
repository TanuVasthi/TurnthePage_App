package com.example.turnthepage.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.turnthepage.MenuBottomSheetFragment

import com.example.turnthepage.R
import com.example.turnthepage.adapter.PopularAdapter


import com.example.turnthepage.databinding.FragmentHomeBinding
import com.example.turnthepage.databinding.PopularItemBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.viewAllMenu.setOnClickListener{
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager,"Test")
        }
        return binding.root




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.bookstore6, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.bookstore2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.bookstore3, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.bookstore5, ScaleTypes.FIT))
        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }
            override fun onItemSelected(position: Int) {
                val itemPosition=imageList[position]
                val itemMessage = "Selected Image $position"
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()

            }
        })
        val item = listOf("The Great Gatsby","To Kill a Mocking Bird","Pride and Prejudice","Moby Dick")
        val Price=listOf("₹300","₹350","₹230","₹250")
        val popularBookImages=listOf(R.drawable.greatgatsby,R.drawable.tokillamockingbird,R.drawable.prideandprejudice,R.drawable.mobydick)
        val adapter= PopularAdapter(item,Price,popularBookImages,requireContext())
        binding.popularRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.popularRecyclerView.adapter=adapter
    }

    companion object {

    }
}



