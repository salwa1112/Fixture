package com.fixtureservicexyz.activities

import android.content.Intent
import android.os.Bundle
import com.fixtureservicexyz.databinding.ActivitySignUpPersonalDataBinding
import com.fixtureservicexyz.models.User
import com.fixtureservicexyz.utils.GoForwardTransition
import com.fixtureservicexyz.utils.StrKeys

class SignUpPersonalDataActivity : BaseActivity() {

    private lateinit var binding:ActivitySignUpPersonalDataBinding

    private var user:User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpPersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUpPersonalDataNext.setOnClickListener {

            if(validateAllFields()){
                Intent(this,SignUpAddressActivity::class.java).apply {
                    this.putExtra(StrKeys.SIGN_UP_PERSONAL_DATA,user)
                    startActivity(this)
                    GoForwardTransition()
                }
            }

        }
    }

    private fun validateAllFields():Boolean{
        val fullName = binding.etFullName.text.toString()
        val email = binding.etEmail.text.toString()
        val phone = binding.etPhone.text.toString()
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        var isFormValid = true


        when{
            fullName.isEmpty()->{
                binding.etFullName.error = "Full name is required"
                isFormValid = false
            }
            email.isEmpty()->{
                binding.etEmail.error = "Email is required"
                isFormValid = false
            }
            phone.isEmpty()->{
                binding.etPhone.error = "Phone is required"
                isFormValid = false
            }
            username.isEmpty()->{
                binding.etUsername.error = "Username is required"
                isFormValid = false
            }
            password.isEmpty()->{
                binding.etPassword.error = "Password is required"
                isFormValid = false
            }
            confirmPassword.isEmpty()->{
                binding.etConfirmPassword.error = "Password confirm is required"
                isFormValid = false
            }
            password != confirmPassword ->{
                binding.etPassword.error = "Passwords are differents"
                binding.etConfirmPassword.error = "Passwords are differents"
                isFormValid = false
            }
        }

        if(isFormValid){
            user = User(fullName,email,phone,username,password)
        }

        return isFormValid
    }

}