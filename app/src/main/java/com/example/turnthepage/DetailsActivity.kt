package com.example.turnthepage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.turnthepage.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookTitle=intent.getStringExtra("MenuItemName")
        val bookImage=intent.getIntExtra("MenuItemImage",0)
        binding.detailBookName.text=bookTitle
        binding.detailBookImage.setImageResource(bookImage)

        binding.imageButton.setOnClickListener{
            finish()
        }
        }
    }
