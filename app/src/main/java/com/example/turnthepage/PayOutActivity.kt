/*package com.example.turnthepage

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.provider.ContactsContract.Profile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.adapter.MenuAdapter
import com.example.turnthepage.databinding.ActivityLoginBinding
import com.example.turnthepage.databinding.ActivityPayOutBinding
import com.example.turnthepage.databinding.FragmentMenuBottomSheetBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PayOutActivity : AppCompatActivity() {
    lateinit var binding: ActivityPayOutBinding

    private lateinit var auth:FirebaseAuth
    private lateinit var name:String
    private lateinit var address:String
    private lateinit var phone:String
    private lateinit var totalAmount:String
    private lateinit var bookItemName:ArrayList<String>
    private lateinit var bookItemPrice:ArrayList<String>
    private lateinit var bookItemImage:ArrayList<String>
    private lateinit var bookItemDescription:ArrayList<String>
    private lateinit var bookItemQuantity:ArrayList<Int>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userId:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        databaseReference=FirebaseDatabase.getInstance().getReference()
        setUserData()
        val intent=intent
        bookItemName=intent.getStringArrayListExtra("BookItemName") as ArrayList<String>
        bookItemPrice=intent.getStringArrayListExtra("BookItemPrice") as ArrayList<String>
        bookItemImage=intent.getStringArrayListExtra("BookItemImage") as ArrayList<String>
        bookItemDescription=intent.getStringArrayListExtra("BookItemDescription") as ArrayList<String>
        bookItemQuantity=intent.getIntegerArrayListExtra("BookItemQuantity") as ArrayList<Int>

        totalAmount=calculateTotalAmount().toString()+"₹"
        //binding.totalAmount.isEnabled=false
        binding.totalAmount.setText(totalAmount)

        binding.editbackbutton.setOnClickListener{
            finish()
        }



        binding.PlaceMyOrder.setOnClickListener{
            val bottomSheetDialog = CongratsBottomSheet()
            bottomSheetDialog.show(supportFragmentManager,"Test")
        }
    }

    private fun calculateTotalAmount():Int {
        var totalAmount=0
        for(i in 0 until bookItemPrice.size){
            var price=bookItemPrice[i]
            val lastChar =price.last()
            //ctrl+alt+4 for rupee symbol
            val priceIntValue=if(lastChar=='₹'){
                price.dropLast(1).toInt()
            }
            else{
                price.toInt()
            }
            var quantity=bookItemQuantity[i]
            totalAmount+=priceIntValue*quantity
        }
        return totalAmount

    }

    private fun setUserData() {
        val user=auth.currentUser
        if(user!=null){
            val userId=user.uid
            val userReference=databaseReference.child("user").child(userId)
            userReference.addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val names=snapshot.child("name").getValue(String::class.java)?:""
                        val addresses=snapshot.child("address").getValue(String::class.java)?:""
                        val phones=snapshot.child("phone").getValue(String::class.java)?:""

                        binding.apply{
                            name.setText(names)
                            address.setText(addresses)
                            phone.setText(phones)
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }

}
*/
package com.example.turnthepage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turnthepage.adapter.MenuAdapter
import com.example.turnthepage.databinding.ActivityLoginBinding
import com.example.turnthepage.databinding.ActivityPayOutBinding
import com.example.turnthepage.databinding.FragmentMenuBottomSheetBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PayOutActivity : AppCompatActivity() {
    lateinit var binding: ActivityPayOutBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var name: String
    private lateinit var address: String
    private lateinit var phone: String
    private lateinit var totalAmount: String
    private lateinit var bookItemName: ArrayList<String>
    private lateinit var bookItemPrice: ArrayList<Int> // Change to ArrayList<Int>
    private lateinit var bookItemImage: ArrayList<String>
    private lateinit var bookItemDescription: ArrayList<String>
    private lateinit var bookItemQuantity: ArrayList<Int>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference
        setUserData()
        val intent = intent
        bookItemName = intent.getStringArrayListExtra("BookItemName") as ArrayList<String>
        bookItemPrice = intent.getIntegerArrayListExtra("BookItemPrice") as ArrayList<Int> // Change to getIntegerArrayListExtra
        bookItemImage = intent.getStringArrayListExtra("BookItemImage") as ArrayList<String>
        bookItemDescription = intent.getStringArrayListExtra("BookItemDescription") as ArrayList<String>
        bookItemQuantity = intent.getIntegerArrayListExtra("BookItemQuantity") as ArrayList<Int>

        totalAmount = calculateTotalAmount().toString() + "₹"
        binding.totalAmount.setText(totalAmount)

        binding.editbackbutton.setOnClickListener {
            finish()
        }

        binding.PlaceMyOrder.setOnClickListener {
            val bottomSheetDialog = CongratsBottomSheet()
            bottomSheetDialog.show(supportFragmentManager, "Test")
        }
    }

    private fun calculateTotalAmount(): Int {
        var totalAmount = 0
        for (i in 0 until bookItemPrice.size) {
            val price = bookItemPrice[i] // No need to parse as it is already Int
            val quantity = bookItemQuantity[i]
            totalAmount += price * quantity
        }
        return totalAmount
    }

    private fun setUserData() {
        val user = auth.currentUser
        if (user != null) {
            val userId = user.uid
            val userReference = databaseReference.child("user").child(userId)
            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val names = snapshot.child("name").getValue(String::class.java) ?: ""
                        val addresses = snapshot.child("address").getValue(String::class.java) ?: ""
                        val phones = snapshot.child("phone").getValue(String::class.java) ?: ""

                        binding.apply {
                            name.setText(names)
                            address.setText(addresses)
                            phone.setText(phones)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }
}
