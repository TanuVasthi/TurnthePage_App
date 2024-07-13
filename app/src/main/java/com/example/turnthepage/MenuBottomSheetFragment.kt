/*package com.example.turnthepage

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.adapter.CartAdapter
import com.example.turnthepage.adapter.MenuAdapter
import com.example.turnthepage.databinding.FragmentCartBinding
import com.example.turnthepage.databinding.FragmentMenuBottomSheetBinding
import com.example.turnthepage.model.menuItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentMenuBottomSheetBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<MenuItem>
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
        retriveMenuItems()

        return binding.root
    }

    private fun retriveMenuItems() {
        database = FirebaseDatabase.getInstance()
        val bookRef:DatabaseReference=database.reference.child("menu")
        menuItems= mutableListOf()
        bookRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(bookSnapshot in snapshot.children){
                    val menuItem = bookSnapshot.getValue(MenuItem::class.java)
                    menuItem?.let { menuItems.add(it) }
                }
                setAdapter()
            }

            private fun setAdapter() {
                val adapter = MenuAdapter(menuItems, requireContext())
                binding.menuRecyclerView.layoutManager=LinearLayoutManager(requireContext())
                binding.menuRecyclerView.adapter= adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    companion object {

    }
}*/
/*package com.example.turnthepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.adapter.MenuAdapter
import com.example.turnthepage.databinding.FragmentMenuBottomSheetBinding
import com.example.turnthepage.model.menuItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.*

class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<menuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.buttonBack.setOnClickListener {
            dismiss()
        }
        retrieveMenuItems()

        return binding.root
    }

    private fun retrieveMenuItems() {
        database = FirebaseDatabase.getInstance()
        val bookRef: DatabaseReference = database.reference.child("menu")
        menuItems = mutableListOf()
        bookRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (bookSnapshot in snapshot.children) {
                    val menuItem = bookSnapshot.getValue(menuItem::class.java)
                    menuItem?.let { menuItems.add(it) }
                }
                Log.d("ITEMS","onDataChange:Data Recieved")
                setAdapter()
            }


            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors.
            }
        })
    }
    private fun setAdapter() {
        context?.let { ctx ->
            if(menuItems.isNotEmpty()){
                val adapter = MenuAdapter(menuItems, ctx)
                binding.menuRecyclerView.layoutManager = LinearLayoutManager(ctx)
                binding.menuRecyclerView.adapter = adapter
                Log.d("ITEMS","setAdapter:data set")
            }
            else{
                Log.d("ITEMS","setAdapter:data not set")
            }
        }
    }


    companion object {
        // Add any necessary companion object functions or variables here.
    }
}
*/
package com.example.turnthepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.adapter.MenuAdapter
import com.example.turnthepage.databinding.FragmentMenuBottomSheetBinding
import com.example.turnthepage.model.menuItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.*

class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<menuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.buttonBack.setOnClickListener {
            dismiss()
        }
        retrieveMenuItems()

        return binding.root
    }

    private fun retrieveMenuItems() {
        database = FirebaseDatabase.getInstance()
        val bookRef: DatabaseReference = database.reference.child("menu")
        menuItems = mutableListOf()
        bookRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                menuItems.clear()
                Log.d("Firebase", "Snapshot children count: ${snapshot.childrenCount}")
                for (bookSnapshot in snapshot.children) {
                    Log.d("Firebase", "Child key: ${bookSnapshot.key}, value: ${bookSnapshot.value}")
                    try {
                        val menuItem = bookSnapshot.getValue(menuItem::class.java)
                        menuItem?.let { menuItems.add(it) }
                    } catch (e: Exception) {
                        Log.e("Firebase", "Error converting snapshot to menuItem: ${e.message}")
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database read errors here
                Log.e("Firebase", "Error retrieving menu items: ${error.message}")
            }
        })
    }

    private fun setAdapter() {
        context?.let { ctx ->
            if (menuItems.isNotEmpty()) {
                val adapter = MenuAdapter(menuItems, ctx)
                binding.menuRecyclerView.layoutManager = LinearLayoutManager(ctx)
                binding.menuRecyclerView.adapter = adapter
                Log.d("Firebase", "Adapter set with ${menuItems.size} items")
            } else {
                Log.d("Firebase", "No menu items available")
            }
        }
    }

    companion object {
        // Add any necessary companion object functions or variables here.
    }
}
