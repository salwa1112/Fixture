package com.fixtureservicexyz.activities

import android.content.Intent
import android.os.Bundle
import com.fixtureservicexyz.databinding.ActivityGuestLoginBinding
import com.fixtureservicexyz.models.Guest
import com.fixtureservicexyz.utils.GoForwardTransition
import com.fixtureservicexyz.utils.StrKeys

class GuestLoginActivity : BaseActivity() {

    private lateinit var binding:ActivityGuestLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLoginGuestContinue.setOnClickListener {
            val guestPhoneNumber = binding.etPhoneNumberGuest.text.toString()

            if(guestPhoneNumber.isEmpty()){
                binding.etPhoneNumberGuest.error = "Phone number is required"
            }else{
                Intent(this,GuestLocationActivity::class.java).apply {
                    this.putExtra(StrKeys.SIGN_IN_AS_GUEST_PHONE_NUMBER,Guest(phoneNumber = guestPhoneNumber))
                    startActivity(this)
                    GoForwardTransition()
                }
            }

        }
    }
}