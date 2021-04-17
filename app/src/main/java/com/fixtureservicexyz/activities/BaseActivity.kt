package com.fixtureservicexyz.activities

import androidx.appcompat.app.AppCompatActivity
import com.fixtureservicexyz.utils.GoBackTransition

abstract class BaseActivity : AppCompatActivity(){

    override fun onBackPressed() {
        super.onBackPressed()
        GoBackTransition()
    }
}