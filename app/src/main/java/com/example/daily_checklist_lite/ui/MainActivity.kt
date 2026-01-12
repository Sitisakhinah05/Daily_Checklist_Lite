package com.example.daily_checklist_lite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.daily_checklist_lite.R
import com.example.daily_checklist_lite.databinding.ActivityMainBinding
import com.example.daily_checklist_lite.ui.home.HomeFragment
import com.example.daily_checklist_lite.ui.checklist.ChecklistFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> loadFragment(HomeFragment())
                R.id.menu_checklist -> loadFragment(ChecklistFragment())
                R.id.menu_profile -> loadFragment(ProfileFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
