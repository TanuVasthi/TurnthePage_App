package com.example.turnthepage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.turnthepage.databinding.ActivityLoginBinding
import com.example.turnthepage.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class LoginActivity : AppCompatActivity() {
    private var userName:String?=null
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private val binding:ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        //Initialization of Firebase Auth
        auth = Firebase.auth
        database=Firebase.database.reference
        binding.loginbutton.setOnClickListener{


            email=binding.EmailAddressLogin.text.toString().trim()
            password=binding.LoginPassword.text.toString().trim()
            if(email.isBlank()||password.isBlank()){
                Toast.makeText(this,"Please enter the required details",Toast.LENGTH_SHORT).show()
            }else{
                createUser()
                Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
            }


        }
        binding.donthavebutton.setOnClickListener{
            val intent= Intent(this,SignActivity::class.java)
            startActivity(intent)

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun createUser() {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{ task->
            if(task.isSuccessful){
                val user:FirebaseUser?=auth.currentUser
                updateUI(user)
            }else{
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task->
                    if(task.isSuccessful){
                        saveUserData()
                        val user:FirebaseUser?=auth.currentUser
                        updateUI(user)
                    }else{

                        Toast.makeText(this,"Sign in failed",Toast.LENGTH_SHORT).show()

                    }
                }


            }
        }
    }

    private fun saveUserData() {
        email=binding.EmailAddressLogin.text.toString().trim()
        password=binding.LoginPassword.text.toString().trim()
        val user=UserModel(userName,email,password)
        val userId:String=FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userId).setValue(user)
    }

    private fun updateUI(user: FirebaseUser?) {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}