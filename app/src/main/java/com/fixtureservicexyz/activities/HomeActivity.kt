package com.fixtureservicexyz.activities

import android.content.Intent
import android.os.Bundle
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.ActivityHomeBinding
import com.fixtureservicexyz.utils.GoForwardTransition

class HomeActivity : BaseActivity() {

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(3000)

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.root.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            GoForwardTransition()
        }
    }
}