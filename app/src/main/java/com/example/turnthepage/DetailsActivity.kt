/*package com.example.turnthepage

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.turnthepage.databinding.ActivityDetailsBinding
import com.example.turnthepage.model.cartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var bookTitle: String? = null
    private var bookPrice: String? = null
    private var bookDescription: String? = null
    private var bookImage: String? = null
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()

        bookTitle = intent.getStringExtra("MenuItemName")
        bookPrice = intent.getStringExtra("MenuItemPrice")
        bookDescription = intent.getStringExtra("MenuItemDescription")
        bookImage = intent.getStringExtra("MenuItemImage")

        with(binding) {
            detailBookName.text = bookTitle
            detailDescription.text = bookDescription
            Glide.with(this@DetailsActivity).load(Uri.parse(bookImage)).into(detailBookImage)

        }

        binding.imageButton.setOnClickListener {
            finish()
        }
        binding.addItemButton.setOnClickListener{
            addItemToCart()
        }
    }

    private fun addItemToCart() {
        val database = FirebaseDatabase.getInstance().reference
        val userId = auth.currentUser?.uid?:""

        val cartItem = cartItems(bookTitle.toString(),
            bookPrice.toString(),bookDescription.toString(),bookImage.toString(), bookQuantity = 1)

        database.child("user").child(userId).child("CartItems").push().setValue(cartItem).addOnSuccessListener {
            Toast.makeText(this,"Items added into cart successfully",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Items not added into cart successfully",Toast.LENGTH_SHORT).show()
        }
    }
}
*/
package com.example.turnthepage

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.turnthepage.databinding.ActivityDetailsBinding
import com.example.turnthepage.model.cartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var bookTitle: String? = null
    private var bookPrice: Int? = null
    private var bookDescription: String? = null
    private var bookImage: String? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        bookTitle = intent.getStringExtra("MenuItemName")
        bookPrice = intent.getIntExtra("MenuItemPrice", 0)
        bookDescription = intent.getStringExtra("MenuItemDescription")
        bookImage = intent.getStringExtra("MenuItemImage")

        with(binding) {
            detailBookName.text = bookTitle
            detailDescription.text = bookDescription
            Glide.with(this@DetailsActivity).load(Uri.parse(bookImage)).into(detailBookImage)
        }

        binding.imageButton.setOnClickListener {
            finish()
        }
        binding.addItemButton.setOnClickListener {
            addItemToCart()
        }
    }

    private fun addItemToCart() {
        val database = FirebaseDatabase.getInstance().reference
        val userId = auth.currentUser?.uid ?: ""

        val cartItem = cartItems(
            bookTitle.toString(),
            bookPrice,
            bookDescription.toString(),
            bookImage.toString(),
            bookQuantity = 1
        )

        database.child("user").child(userId).child("CartItems").push().setValue(cartItem).addOnSuccessListener {
            Toast.makeText(this, "Items added into cart successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Items not added into cart successfully", Toast.LENGTH_SHORT).show()
        }
    }
}

