package com.fixtureservicexyz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentConfirmAddressBinding
import com.fixtureservicexyz.utils.StrKeys
import com.fixtureservicexyz.utils.fetchToSharedPrefs


class ConfirmAddressFragment : Fragment(R.layout.fragment_confirm_address) {

    private lateinit var binding:FragmentConfirmAddressBinding
    private var selectedDate:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().getString("DateConfirmation").let {
            this.selectedDate = it.orEmpty()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentConfirmAddressBinding.bind(view)

        binding.btnConfirmAddress.setOnClickListener {
            findNavController().navigate(R.id.action_confirmAddressFragment_to_orderConfirmationFragment)
        }

        binding.tvCustomerAddressConfirmAddress.text = "${fetchCurrentAddress()}"
        binding.tvSelectedDateConfirm.text = "Your date selected:\n $selectedDate"
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