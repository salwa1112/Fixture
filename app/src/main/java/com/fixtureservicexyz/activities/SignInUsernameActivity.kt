package com.fixtureservicexyz.activities

import android.content.Intent
import android.os.Bundle
import com.fixtureservicexyz.databinding.ActivitySignInUsernameBinding
import com.fixtureservicexyz.utils.GoForwardTransition
import com.fixtureservicexyz.utils.StrKeys

class SignInUsernameActivity : BaseActivity() {

    private lateinit var binding: ActivitySignInUsernameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInUsernameBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSignInUsernameNext.setOnClickListener {

            val username = binding.etSignInUsername.text.toString()

            if (username.isEmpty()) {
                binding.etSignInUsername.error = "Username is required"
            } else {
                Intent(this, SignInPasswordActivity::class.java).apply {
                    putExtra(StrKeys.SIGN_IN_USER_NAME,username)
                    startActivity(this)
                    GoForwardTransition()
                }
            }
        }
    }
}