/*package com.example.turnthepage.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.CongratsBottomSheet
import com.example.turnthepage.PayOutActivity
import com.example.turnthepage.R
import com.example.turnthepage.adapter.CartAdapter
import com.example.turnthepage.databinding.FragmentCartBinding
import com.example.turnthepage.model.cartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var bookTitles:MutableList<String>
    private lateinit var bookPrices:MutableList<Int>
    private lateinit var bookDescriptions:MutableList<String>
    private lateinit var bookImagesUri: MutableList<String>
    private lateinit var quantity:MutableList<Int>
    private lateinit var cartAdapter: CartAdapter
    private lateinit var userId:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container,false)

        auth=FirebaseAuth.getInstance()
        retrieveCartItems()




        binding.proceedButton.setOnClickListener{
            val intent = Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    private fun retrieveCartItems() {
        database=FirebaseDatabase.getInstance()
        userId=auth.currentUser?.uid?:""
        val bookReference:DatabaseReference=database.reference.child("user").child(userId).child("CartItems")


        bookTitles= mutableListOf()
        bookPrices= mutableListOf()
        bookDescriptions= mutableListOf()
        bookImagesUri= mutableListOf()
        quantity= mutableListOf()

        bookReference.addListenerForSingleValueEvent(object :ValueEventListener{
           override fun onDataChange(snapshot: DataSnapshot) {
                for(bookSnapshot in snapshot.children){
                    val cartItems=bookSnapshot.getValue(cartItems::class.java)

                    cartItems?.bookTitle?.let{bookTitles.add(it)}
                    cartItems?.bookPrice?.let{bookPrices.add(it)}
                    cartItems?.bookDescription?.let{bookDescriptions.add(it)}
                    cartItems?.bookImage?.let{bookImagesUri.add(it)}
                    cartItems?.bookQuantity?.let{quantity.add(it)}
                }
                setAdapter()
            }

            private fun setAdapter() {
                val adapter=CartAdapter(requireContext(),bookTitles,bookPrices,bookDescriptions,bookImagesUri,quantity)
                binding.cartRecyclerBin.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                binding.cartRecyclerBin.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(context,"data not fetch",Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {

    }
}*/
package com.example.turnthepage.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.PayOutActivity
import com.example.turnthepage.adapter.CartAdapter
import com.example.turnthepage.databinding.FragmentCartBinding
import com.example.turnthepage.model.cartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var bookTitles: MutableList<String>
    private lateinit var bookPrices: MutableList<String>
    private lateinit var bookDescriptions: MutableList<String>
    private lateinit var bookImagesUri: MutableList<String>
    private lateinit var quantity: MutableList<Int>
    private lateinit var cartAdapter: CartAdapter
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        userId = auth.currentUser?.uid ?: ""

        retrieveCartItems()

        binding.proceedButton.setOnClickListener {
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            startActivity(intent)
        }
    }

    private fun retrieveCartItems() {
        val bookReference: DatabaseReference =
            database.reference.child("user").child(userId).child("CartItems")

        bookTitles = mutableListOf()
        bookPrices = mutableListOf()
        bookDescriptions = mutableListOf()
        bookImagesUri = mutableListOf()
        quantity = mutableListOf()

        bookReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (bookSnapshot in snapshot.children) {
                    val cartItem = bookSnapshot.getValue(cartItems::class.java)
                    cartItem?.let {
                        it.bookTitle?.let { title ->
                            bookTitles.add(title)
                        }
                        it.bookPrice?.let { price ->
                            bookPrices.add(price)
                        }
                        it.bookDescription?.let { description ->
                            bookDescriptions.add(description)
                        }
                        it.bookImage?.let { imageUri ->
                            bookImagesUri.add(imageUri)
                        }
                        it.bookQuantity?.let { qty ->
                            quantity.add(qty)
                        }
                    }
                }
                // After populating lists, set the adapter
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to retrieve cart items", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setAdapter() {
        // Check if lists are not empty before setting adapter
        if (bookTitles.isNotEmpty()) {
            cartAdapter = CartAdapter(requireContext(), bookTitles, bookPrices, bookDescriptions, bookImagesUri, quantity)
            binding.cartRecyclerBin.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.cartRecyclerBin.adapter = cartAdapter
        } else {
            // Handle case where no items were retrieved
            Toast.makeText(context, "No items found in cart", Toast.LENGTH_SHORT).show()
        }
    }
}
