package com.fixtureservicexyz.activities

import android.content.Intent
import android.os.Bundle
import com.fixtureservicexyz.MainActivity
import com.fixtureservicexyz.databinding.ActivityGuestLocationBinding
import com.fixtureservicexyz.models.Address
import com.fixtureservicexyz.models.Guest
import com.fixtureservicexyz.utils.*

class GuestLocationActivity : BaseActivity() {

    private lateinit var binding:ActivityGuestLocationBinding

    private var guest:Guest? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityGuestLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Guest>(StrKeys.SIGN_IN_AS_GUEST_PHONE_NUMBER)?.let {
            this.guest = it
        }

        binding.btnGuestStart.setOnClickListener {

            if (validateAllFields()) {
                //Save into Shared Preferences as GUEST
                val isSaved = addToSharedPrefs(guest!!)
                UserType.setUserType(UserEnum.GUEST)
                if (isSaved) {
                    Intent(this, MainActivity::class.java).apply {
                        startActivity(this)
                        finish()
                        GoForwardTransition()
                    }
                }
            }
        }
    }

    private fun validateAllFields(): Boolean {
        val apartmentHouseNumber = binding.etGuestApartment.text.toString()
        val street = binding.etGuestStreet.text.toString()
        val city = binding.etGuestCity.text.toString()
        val province = binding.etGuestProvince.text.toString()
        val zipCode = binding.etGuestZipCode.text.toString()
        var isFormValid = true


        when {
            apartmentHouseNumber.isEmpty() -> {
                binding.etGuestApartment.error = "Apartment/House no. is required"
                isFormValid = false
            }
            street.isEmpty() -> {
                binding.etGuestStreet.error = "Street is required"
                isFormValid = false
            }
            city.isEmpty() -> {
                binding.etGuestCity.error = "City is required"
                isFormValid = false
            }
            province.isEmpty() -> {
                binding.etGuestProvince.error = "Province is required"
                isFormValid = false
            }
            zipCode.isEmpty() -> {
                binding.etGuestZipCode.error = "Postal Code is required"
                isFormValid = false
            }
        }

        if (isFormValid){
            val guestAddress = Address(apartmentHouseNumber,street,city,province,zipCode)
            guest?.address = guestAddress
        }

        return isFormValid
    }
}