package com.fixtureservicexyz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.fixtureservicexyz.MainActivity
import com.fixtureservicexyz.databinding.ActivitySignInPasswordBinding
import com.fixtureservicexyz.utils.*

class SignInPasswordActivity : BaseActivity() {

    private lateinit var binding: ActivitySignInPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInPasswordBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var username = intent.getStringExtra(StrKeys.SIGN_IN_USER_NAME)

        username = username ?: username.orEmpty()

        binding.btnSignInPassword.setOnClickListener {
            val password = binding.etSignInPassword.text.toString()

            if (password.isEmpty()) {
                binding.etSignInPassword.error = "Password is required"
            } else {
                val _username: String? = fetchToSharedPrefs(StrKeys.USER_NAME_SHARED_PREFS)
                val _password: String? = fetchToSharedPrefs(StrKeys.USER_PASS_SHARED_PREFS)

                when {
                    _username == null || _password == null ->
                        Toast.makeText(this, "USER AND PASS ARE WRONG", Toast.LENGTH_SHORT).show()
                    username != _username || password != _password ->
                        Toast.makeText(this, "USER AND PASS ARE WRONG", Toast.LENGTH_SHORT).show()
                    username == _username && password == _password -> Intent(
                        this,
                        MainActivity::class.java
                    ).apply {
                        UserType.setUserType(UserEnum.USER)
                        startActivity(this)
                        finish()
                        GoForwardTransition()
                    }
                }
            }
        }


    }
}