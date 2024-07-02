package com.example.turnthepage

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.adapter.CartAdapter
import com.example.turnthepage.adapter.MenuAdapter
import com.example.turnthepage.databinding.FragmentCartBinding
import com.example.turnthepage.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentMenuBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container,false)

        binding.buttonBack.setOnClickListener(){
            dismiss()
        }

        val menuBookTitle = listOf("Pride and Prejudice","Moby-Dick","Great Gatsby")
        val menuItemPrice = listOf("200","320","300")
        val menuImage =listOf(
            R.drawable.prideandprejudice,
            R.drawable.mobydick,
            R.drawable.greatgatsby
        )
        val adapter = MenuAdapter(ArrayList(menuBookTitle), ArrayList(menuItemPrice), ArrayList(menuImage),requireContext())

        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter
        return binding.root
    }

    companion object {

    }
}