package com.example.turnthepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var NavController:NavController=findNavController(R.id.fragmentContainerView2)
        var bottomnav:BottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigationBar)
        bottomnav.setupWithNavController(NavController)
    }
}
