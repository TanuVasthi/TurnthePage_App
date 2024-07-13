package com.example.turnthepage.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.turnthepage.MenuBottomSheetFragment
import com.example.turnthepage.R
import com.example.turnthepage.adapter.MenuAdapter
import com.example.turnthepage.model.menuItem
import com.example.turnthepage.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var database:FirebaseDatabase
    private lateinit var menuItems: MutableList<menuItem>
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
        //retrieve and display popular menu items
        retrieveAndDisplayPopularItems()
        return binding.root




    }

    private fun retrieveAndDisplayPopularItems() {
        //get reference to database
        database=FirebaseDatabase.getInstance()
        val bookRef:DatabaseReference=database.reference.child("menu")
        menuItems= mutableListOf()

        bookRef.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(bookSnapshot in snapshot.children){
                    val menuItem=bookSnapshot.getValue(menuItem::class.java)
                    menuItem?.let{
                        menuItems.add(it)
                    }
                }
                //display random popular books
                randomPopularItems()
            }

            private fun randomPopularItems() {
                val index=menuItems.indices.toList().shuffled()
                val numItemToShow=6
                val subsetMenuItems=index.take(numItemToShow).map { menuItems[it] }

                setPopularItemsAdapter(subsetMenuItems)
            }

            private fun setPopularItemsAdapter(subsetMenuItems: List<menuItem>) {
                val adapter= MenuAdapter(subsetMenuItems.toMutableList(),requireContext())
                binding.popularRecyclerView.layoutManager= LinearLayoutManager(requireContext())
                binding.popularRecyclerView.adapter=adapter

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
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


    }

    companion object {

    }
}



