package com.fixtureservicexyz.activities

import android.content.Intent
import android.os.Bundle
import com.fixtureservicexyz.databinding.ActivitySignUpAddressBinding
import com.fixtureservicexyz.models.Address
import com.fixtureservicexyz.models.User
import com.fixtureservicexyz.utils.GoForwardTransition
import com.fixtureservicexyz.utils.StrKeys

class SignUpAddressActivity : BaseActivity() {

    private lateinit var binding: ActivitySignUpAddressBinding

    private var user: User? = null
    private var address: Address? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpAddressBinding.inflate(layoutInflater)

        setContentView(binding.root)

        intent.getParcelableExtra<User>(StrKeys.SIGN_UP_PERSONAL_DATA)?.let {
            user = it
        }

        binding.btnSignUpAddressContinue.setOnClickListener {
            if (validateAllFields()) {
                Intent(this,SignUpCreatingAccountActivity::class.java).apply {
                    this.putExtra(StrKeys.SIGN_UP_PERSONAL_DATA_wITH_ADDRESS,user)
                    startActivity(this)
                    GoForwardTransition()
                }
            }
        }
    }
    private fun validateAllFields(): Boolean {
        val apartmentHouseNumber = binding.etApartmentHouseNumber.text.toString()
        val street = binding.etStreetName.text.toString()
        val city = binding.etCity.text.toString()
        val province = binding.etProvince.text.toString()
        val zipCode = binding.etZipCode.text.toString()
        var isFormValid = true


        when {
            apartmentHouseNumber.isEmpty() -> {
                binding.etApartmentHouseNumber.error = "Apartment/House no. is required"
                isFormValid = false
            }
            street.isEmpty() -> {
                binding.etStreetName.error = "Street is required"
                isFormValid = false
            }
            city.isEmpty() -> {
                binding.etCity.error = "City is required"
                isFormValid = false
            }
            province.isEmpty() -> {
                binding.etProvince.error = "Province is required"
                isFormValid = false
            }
            zipCode.isEmpty() -> {
                binding.etZipCode.error = "Postal Code is required"
                isFormValid = false
            }
        }

        if (isFormValid) {
            address = Address(apartmentHouseNumber, street, city, province, zipCode)
            user?.address = address
        }

        return isFormValid
    }
}


