package com.fixtureservicexyz.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.fixtureservicexyz.MainActivity
import com.fixtureservicexyz.databinding.ActivitySignUpCreatingAccountBinding
import com.fixtureservicexyz.models.User
import com.fixtureservicexyz.utils.*

class SignUpCreatingAccountActivity : BaseActivity() {

    private lateinit var binding:ActivitySignUpCreatingAccountBinding
    
    private var isSaved = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpCreatingAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        intent.getParcelableExtra<User>(StrKeys.SIGN_UP_PERSONAL_DATA_wITH_ADDRESS).let { 
           
            isSaved = addToSharedPrefs(it!!)
        }
        

        Handler(Looper.getMainLooper()).postDelayed({
            // Create an intent to launch MainActivity
            if (isSaved) {
                UserType.setUserType(UserEnum.USER)
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                    GoForwardTransition()
                }
            }

        }, 3000)
    }
}