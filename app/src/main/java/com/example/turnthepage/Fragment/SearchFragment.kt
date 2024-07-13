package com.example.turnthepage.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.R
import com.example.turnthepage.adapter.MenuAdapter
import com.example.turnthepage.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var binding:FragmentSearchBinding
    private lateinit var adapter: MenuAdapter
    private val originalMenuBookName :List<String> = listOf("Pride and Prejudice","Moby-Dick","Great Gatsby")
    private val originalMenuItemPrice :List<String> = listOf("200","320","300")
    private val originalMenuImage:List<Int> =listOf(R.drawable.prideandprejudice, R.drawable.mobydick, R.drawable.greatgatsby)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val filteredMenuBookName = mutableListOf<String>()
    private val filteredMenuItemPrice = mutableListOf<String>()
    private val filteredMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        //adapter = MenuAdapter(filteredMenuBookName,filteredMenuItemPrice,filteredMenuImage,requireContext())
        binding.menuRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter=adapter

        setupSearchView()
        showAllMenu()
        return binding.root
    }



    private fun showAllMenu() {
        filteredMenuBookName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        filteredMenuBookName.addAll(originalMenuBookName)
        filteredMenuItemPrice.addAll(originalMenuItemPrice)
        filteredMenuImage.addAll(originalMenuImage)

        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })

    }

    private fun filterMenuItems(query: String) {
        filteredMenuBookName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()
        
        originalMenuBookName.forEachIndexed { index, bookName ->
            if(bookName.contains(query,ignoreCase = true)){
                filteredMenuBookName.add(bookName)
                filteredMenuItemPrice.add(originalMenuItemPrice[index])
                filteredMenuImage.add(originalMenuImage[index])
            }
        }
        adapter.notifyDataSetChanged()
    }

    companion object {

    }
}