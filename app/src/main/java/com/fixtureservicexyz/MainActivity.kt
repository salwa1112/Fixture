package com.fixtureservicexyz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fixtureservicexyz.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var toolbar: Toolbar
    private lateinit var botttomNavigationView:BottomNavigationView

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpComponents()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig)
    }

    private fun setUpComponents(){
        //Setup ToolBar
        toolbar = binding.toolbar
        toolbar.visibility = View.VISIBLE
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.fragment)
        appBarConfig = AppBarConfiguration.Builder(
            R.id.dashboardFragment,R.id.profileFragment,R.id.aboutAppFragment
        ).build()

        botttomNavigationView = binding.bottomNavView
        botttomNavigationView.setupWithNavController(navController)

        setupActionBarWithNavController(navController, appBarConfig)
    }
}