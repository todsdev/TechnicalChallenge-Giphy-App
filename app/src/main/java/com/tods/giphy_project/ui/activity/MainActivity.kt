package com.tods.giphy_project.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.tods.giphy_project.R
import com.tods.giphy_project.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configBinding()
        configView()
        configToolbar()
    }

    private fun configToolbar() {
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.logo_smaller)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = getString(R.string.empty)
    }

    private fun configView() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val controller = navHostFragment.navController
        binding.bottomNavigation.apply {
            setupWithNavController(controller)
            NavigationBarView.OnItemReselectedListener { }
        }
    }

    private fun configBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}