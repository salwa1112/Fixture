package com.fixtureservicexyz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fixtureservicexyz.MainActivity
import com.fixtureservicexyz.databinding.ActivityGoogleUserLocationBinding
import com.fixtureservicexyz.models.Address
import com.fixtureservicexyz.models.GoogleAccount
import com.fixtureservicexyz.utils.*

class GoogleUserLocationActivity : AppCompatActivity() {

    private lateinit var binding:ActivityGoogleUserLocationBinding

    private lateinit var googleUserAccount:GoogleAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getParcelableExtra<GoogleAccount>(StrKeys.SIGN_IN_AS_GOOGLE_ACCOUNT_EMAIL)?.let {
            this.googleUserAccount = it
        }

        binding = ActivityGoogleUserLocationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnGoogleUserStart.setOnClickListener {

            if(validateAllFields()){
                val isSaved = addToSharedPrefs(googleUserAccount!!)
                UserType.setUserType(UserEnum.GMAIL)
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
        val apartmentHouseNumber = binding.etGoogleUserApartment.text.toString()
        val street = binding.etGoogleUserStreet.text.toString()
        val city = binding.etGoogleUserCity.text.toString()
        val province = binding.etGoogleUserProvince.text.toString()
        val zipCode = binding.etGoogleUserZipCode.text.toString()
        var isFormValid = true


        when {
            apartmentHouseNumber.isEmpty() -> {
                binding.etGoogleUserApartment.error = "Apartment/House no. is required"
                isFormValid = false
            }
            street.isEmpty() -> {
                binding.etGoogleUserStreet.error = "Street is required"
                isFormValid = false
            }
            city.isEmpty() -> {
                binding.etGoogleUserCity.error = "City is required"
                isFormValid = false
            }
            province.isEmpty() -> {
                binding.etGoogleUserProvince.error = "Province is required"
                isFormValid = false
            }
            zipCode.isEmpty() -> {
                binding.etGoogleUserZipCode.error = "Postal Code is required"
                isFormValid = false
            }
        }

        if (isFormValid){
            val googleUserAddress = Address(apartmentHouseNumber,street,city,province,zipCode)
            googleUserAccount.address = googleUserAddress
        }

        return isFormValid
    }
}