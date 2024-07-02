package com.example.turnthepage

import android.os.Bundle
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

class PayOutActivity : AppCompatActivity() {
    lateinit var binding: ActivityPayOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.PlaceMyOrder.setOnClickListener{
            val bottomSheetDialog = CongratsBottomSheet()
            bottomSheetDialog.show(supportFragmentManager,"Test")
        }
    }

}
