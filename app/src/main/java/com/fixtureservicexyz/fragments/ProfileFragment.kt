package com.fixtureservicexyz.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fixtureservicexyz.R
import com.fixtureservicexyz.activities.LoginActivity
import com.fixtureservicexyz.databinding.FragmentProfileBinding
import com.fixtureservicexyz.utils.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        val userType = UserType.getUserType()

        when (userType) {
            UserEnum.GUEST -> {
                binding.llContainerGuest.show()
                val phone =
                    requireContext().fetchToSharedPrefs(StrKeys.GUEST_PHONE_NUMBER_SHARED_PREFS)
                        .orEmpty()
                binding.etPhoneInfoGuest.text = phone.toEditable()
                binding.tvAddress.text = fetchCurrentAddress()

            }
            UserEnum.USER -> {
                binding.llContainerUserRegistered.show()
                val username =
                    requireContext().fetchToSharedPrefs(StrKeys.USER_NAME_SHARED_PREFS).orEmpty()
                val fullName = requireContext().fetchToSharedPrefs(StrKeys.USER_FULL_NAME_SHARED_PREFS).orEmpty()
                val email = requireContext().fetchToSharedPrefs(StrKeys.USER_EMAIL_SHARED_PREFS).orEmpty()

                binding.etUserNameInfo.text = username.toEditable()
                binding.etFullNameInfo.text = fullName.toEditable()
                binding.etEmailInfo.text = email.toEditable()
                binding.tvAddress.text = fetchCurrentAddress()

            }
            UserEnum.GMAIL->{
                binding.llContainerGuest.show()
                val email =
                    requireContext().fetchToSharedPrefs(StrKeys.GOOGLE_EMAIL_SHARED_PREFS)
                        .orEmpty()
                binding.etPhoneInfoGuest.text = email.toEditable()
                binding.tvAddress.text = fetchCurrentAddress()
            }
        }

        binding.etSignOutProfile.setOnClickListener {
            Intent(requireActivity(), LoginActivity::class.java).apply {
                requireActivity().finish()
                startActivity(this)
                (requireActivity() as AppCompatActivity).GoForwardTransition()
            }
        }
    }

    fun fetchCurrentAddress():String{
        return with(requireContext()){
            val apartmentNumber = fetchToSharedPrefs(StrKeys.APARTMENT_ADDRESS_SHARED_PREFS).orEmpty()
            val street = fetchToSharedPrefs(StrKeys.STREET_ADDRESS_SHARED_PREFS).orEmpty()
            val city = fetchToSharedPrefs(StrKeys.CITY_ADDRESS_SHARED_PREFS).orEmpty()
            val province = fetchToSharedPrefs(StrKeys.PROVINCE_ADDRESS_SHARED_PREFS).orEmpty()
            val zipCode = fetchToSharedPrefs(StrKeys.ZIP_CODE_ADDRESS_SHARED_PREFS)

            "$apartmentNumber $street Street, $city"
        }
    }
}