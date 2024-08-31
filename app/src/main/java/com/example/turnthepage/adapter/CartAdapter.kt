/*package com.example.turnthepage.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.turnthepage.adapter.CartAdapter.CartViewHolder
import com.example.turnthepage.databinding.ActivityCartItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.internal.OpDescriptor

class CartAdapter(
    private val context:Context,
    private val cartItems:MutableList<String>,
    private val cartItemPrices:MutableList<Int>,
    private val cartImages:MutableList<String>,
    private val cartDescriptions:MutableList<String>,
    private val cartQuantity:MutableList<Int>
) : RecyclerView.Adapter<CartViewHolder>() {

    private var auth=FirebaseAuth.getInstance()
    init{
        val database=FirebaseDatabase.getInstance()
        val userId=auth.currentUser?.uid?:""
        val cartItemNumber=cartItems.size

        itemQuantities=IntArray(cartItemNumber){1}
        cartItemsReference=database.reference.child("user").child(userId).child("CartItems")


    }
    companion object{
        private var itemQuantities:IntArray= intArrayOf()
        private lateinit var cartItemsReference: DatabaseReference
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            ActivityCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = cartItems.size
    inner class CartViewHolder(private val binding: ActivityCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartBookName.text = cartItems[position]
                cartItemPrice.text=cartItemPrices[position].toString()

                val uriString=cartImages[position]
                val uri= Uri.parse(uriString)
                Glide.with(context).load(uri).into(cartImage)
                cartItemQuantity.text = quantity.toString()
                minusButton.setOnClickListener {
                    decreaseQuantity(position)
                }
                plusButton.setOnClickListener {
                    increaseQuantity(position)
                }
                deleteButton.setOnClickListener {
                    val itemPosition=adapterPosition
                    if(itemPosition!=RecyclerView.NO_POSITION){
                        deleteItem(itemPosition)
                    }
                }
            }
        }
                    private fun decreaseQuantity(position: Int) {
                        if (itemQuantities[position] > 1) {
                            itemQuantities[position]--
                            binding.cartItemQuantity.text = itemQuantities[position].toString()
                        }
                    }

                        private fun increaseQuantity(position: Int) {
                            if (itemQuantities[position] < 10) {
                                itemQuantities[position]++
                                binding.cartItemQuantity.text = itemQuantities[position].toString()
                            }
                        }
                            private fun deleteItem(position: Int){
                                val positionRetrieve=position
                                getUniqueKeyAtPosition(positionRetrieve){uniqueKey->
                                    if(uniqueKey!=null){
                                        removeItem(position,uniqueKey)
                                    }
                                }
                            }

        private fun removeItem(position: Int, uniqueKey: String) {
            if(uniqueKey!=null){
                cartItemsReference.child(uniqueKey).removeValue().addOnSuccessListener {
                    cartItems.removeAt(position)
                    cartImages.removeAt(position)
                    cartDescriptions.removeAt(position)
                    cartQuantity.removeAt(position)
                    cartItemPrices.removeAt(position)
                    Toast.makeText(context,"Item Deleted Successfully",Toast.LENGTH_SHORT).show()
                    //update item quantity

                    itemQuantities= itemQuantities.filterIndexed { index, i -> index!=position }.toIntArray()
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,cartItems.size)
                }.addOnFailureListener{
                    Toast.makeText(context,"Failed to Delete",Toast.LENGTH_SHORT).show()
                }
            }

        }

        private fun getUniqueKeyAtPosition(positionRetrieve: Int,onComplete:(String?)->Unit) {
            cartItemsReference.addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var uniqueKey:String?=null
                    snapshot.children.forEachIndexed{index,dataSnapshot->
                        if(index==positionRetrieve){
                            uniqueKey=dataSnapshot.key
                            return@forEachIndexed
                        }

                    }
                    onComplete(uniqueKey)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        }
    }

}
*/
/*package com.example.turnthepage.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.turnthepage.databinding.ActivityCartItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CartAdapter(
    private val context: Context,
    private val cartItems: MutableList<String>,
    private val cartItemPrices: MutableList<Int>,
    private val cartImages: MutableList<String>,
    private val cartDescriptions: MutableList<String>,
    private val cartQuantity: MutableList<Int>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val auth = FirebaseAuth.getInstance()
    private val cartItemsReference: DatabaseReference

    init {
        val database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid ?: ""
        cartItemsReference = database.reference.child("user").child(userId).child("CartItems")
        itemQuantities = IntArray(cartItems.size) { 1 }
    }

    companion object {
        private lateinit var itemQuantities: IntArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ActivityCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = cartItems.size

    inner class CartViewHolder(private val binding: ActivityCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartBookName.text = cartItems[position]
                cartItemPrice.text = cartItemPrices[position].toString()
                val uri = Uri.parse(cartImages[position])
                Glide.with(context).load(uri).listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("Glide","onLoadFailed:Image loading failed")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("Glide","onLoadFailed:Image loading success")
                        return false
                    }
                }).into(cartImage)
                cartItemQuantity.text = quantity.toString()
                minusButton.setOnClickListener {
                    decreaseQuantity(position)
                }
                plusButton.setOnClickListener {
                    increaseQuantity(position)
                }
                deleteButton.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        deleteItem(adapterPosition)
                    }
                }
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun deleteItem(position: Int) {
            getUniqueKeyAtPosition(position) { uniqueKey ->
                uniqueKey?.let { removeItem(position, it) }
            }
        }

        private fun removeItem(position: Int, uniqueKey: String) {
            cartItemsReference.child(uniqueKey).removeValue().addOnSuccessListener {
                cartItems.removeAt(position)
                cartItemPrices.removeAt(position)
                cartImages.removeAt(position)
                cartDescriptions.removeAt(position)
                cartQuantity.removeAt(position)
                itemQuantities = itemQuantities.filterIndexed { index, _ -> index != position }.toIntArray()
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, cartItems.size)
                Toast.makeText(context, "Item Deleted Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show()
            }
        }

        private fun getUniqueKeyAtPosition(position: Int, onComplete: (String?) -> Unit) {
            cartItemsReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var uniqueKey: String? = null
                    snapshot.children.forEachIndexed { index, dataSnapshot ->
                        if (index == position) {
                            uniqueKey = dataSnapshot.key
                            return@forEachIndexed
                        }
                    }
                    onComplete(uniqueKey)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }
}
*/
/*
package com.example.turnthepage.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.turnthepage.databinding.ActivityCartItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CartAdapter(
    private val context: Context,
    private val cartItems: MutableList<String>,
    private val cartItemPrices: MutableList<String>,
    private val cartDescriptions: MutableList<String>,
    private val cartImages: MutableList<String>,
    private val cartQuantity: MutableList<Int>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val auth = FirebaseAuth.getInstance()
    private val cartItemsReference: DatabaseReference

    init {
        val database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid ?: ""
        cartItemsReference = database.reference.child("user").child(userId).child("CartItems")
        itemQuantities = IntArray(cartItems.size) { 1 }
    }

    companion object {
        private lateinit var itemQuantities: IntArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ActivityCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        if (position in cartItems.indices) {
            holder.bind(position)
        } else {
            Log.e("CartAdapter", "Invalid position: $position")
        }
    }

    override fun getItemCount(): Int = cartItems.size

    fun getUpdatedItemQuantities():MutableList<Int> {
        val itemQuantity= mutableListOf<Int>()
        itemQuantity.addAll(cartQuantity)
        return itemQuantity

    }

    inner class CartViewHolder(private val binding: ActivityCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartBookName.text = cartItems[position]
                cartItemPrice.text = cartItemPrices[position]
                val uri = Uri.parse(cartImages[position])
                Glide.with(context).load(uri).into(cartImage)
                cartItemQuantity.text = quantity.toString()
                minusButton.setOnClickListener {
                    decreaseQuantity(position)
                }
                plusButton.setOnClickListener {
                    increaseQuantity(position)
                }
                deleteButton.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        deleteItem(adapterPosition)
                    }
                }
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                cartQuantity[position]= itemQuantities[position]
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                cartQuantity[position]= itemQuantities[position]
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun deleteItem(position: Int) {
            getUniqueKeyAtPosition(position) { uniqueKey ->
                uniqueKey?.let { removeItem(position, it) }
            }
        }

        private fun removeItem(position: Int, uniqueKey: String) {
            cartItemsReference.child(uniqueKey).removeValue().addOnSuccessListener {
                cartItems.removeAt(position)
                cartItemPrices.removeAt(position)
                cartImages.removeAt(position)
                cartDescriptions.removeAt(position)
                cartQuantity.removeAt(position)
                itemQuantities = itemQuantities.filterIndexed { index, _ -> index != position }.toIntArray()
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, cartItems.size)
                Toast.makeText(context, "Item Deleted Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show()
            }
        }

        private fun getUniqueKeyAtPosition(position: Int, onComplete: (String?) -> Unit) {
            cartItemsReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var uniqueKey: String? = null
                    snapshot.children.forEachIndexed { index, dataSnapshot ->
                        if (index == position) {
                            uniqueKey = dataSnapshot.key
                            return@forEachIndexed
                        }
                    }
                    onComplete(uniqueKey)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("CartAdapter", "Error getting unique key: ${error.message}")
                    onComplete(null)
                }
            })
        }
    }
}*/
package com.example.turnthepage.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.turnthepage.databinding.ActivityCartItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CartAdapter(
    private val context: Context,
    private val cartItems: MutableList<String>,
    private val cartItemPrices: MutableList<Int>, // Change to Int
    private val cartDescriptions: MutableList<String>,
    private val cartImages: MutableList<String>,
    private val cartQuantity: MutableList<Int>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val auth = FirebaseAuth.getInstance()
    private val cartItemsReference: DatabaseReference

    init {
        val database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid ?: ""
        cartItemsReference = database.reference.child("user").child(userId).child("CartItems")
        itemQuantities = IntArray(cartItems.size) { 1 }
    }

    companion object {
        private lateinit var itemQuantities: IntArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ActivityCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        if (position in cartItems.indices) {
            holder.bind(position)
        } else {
            Log.e("CartAdapter", "Invalid position: $position")
        }
    }

    override fun getItemCount(): Int = cartItems.size

    fun getUpdatedItemQuantities(): MutableList<Int> {
        val itemQuantity = mutableListOf<Int>()
        itemQuantity.addAll(cartQuantity)
        return itemQuantity
    }

    inner class CartViewHolder(private val binding: ActivityCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartBookName.text = cartItems[position]
                cartItemPrice.text = cartItemPrices[position].toString()
                val uri = Uri.parse(cartImages[position])
                Glide.with(context).load(uri).into(cartImage)
                cartItemQuantity.text = quantity.toString()
                minusButton.setOnClickListener { decreaseQuantity(position) }
                plusButton.setOnClickListener { increaseQuantity(position) }
                deleteButton.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        deleteItem(adapterPosition)
                    }
                }
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                cartQuantity[position] = itemQuantities[position]
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                cartQuantity[position] = itemQuantities[position]
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun deleteItem(position: Int) {
            getUniqueKeyAtPosition(position) { uniqueKey ->
                uniqueKey?.let { removeItem(position, it) }
            }
        }

        private fun removeItem(position: Int, uniqueKey: String) {
            cartItemsReference.child(uniqueKey).removeValue().addOnSuccessListener {
                cartItems.removeAt(position)
                cartItemPrices.removeAt(position)
                cartImages.removeAt(position)
                cartDescriptions.removeAt(position)
                cartQuantity.removeAt(position)
                itemQuantities = itemQuantities.filterIndexed { index, _ -> index != position }.toIntArray()
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, cartItems.size)
                Toast.makeText(context, "Item Deleted Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show()
            }
        }

        private fun getUniqueKeyAtPosition(position: Int, onComplete: (String?) -> Unit) {
            cartItemsReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var uniqueKey: String? = null
                    snapshot.children.forEachIndexed { index, dataSnapshot ->
                        if (index == position) {
                            uniqueKey = dataSnapshot.key
                            return@forEachIndexed
                        }
                    }
                    onComplete(uniqueKey)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("CartAdapter", "Error getting unique key: ${error.message}")
                    onComplete(null)
                }
            })
        }
    }
}
