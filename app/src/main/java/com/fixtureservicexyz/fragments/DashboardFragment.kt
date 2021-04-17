package com.fixtureservicexyz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentDashboardBinding
import com.fixtureservicexyz.utils.StrKeys
import com.fixtureservicexyz.utils.fetchToSharedPrefs


class DashboardFragment : Fragment() {

    private lateinit var binding:FragmentDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvPlumbingDashboard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_plumbingServicesFragment)
        }

        binding.cvElectricService.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_electricServicesFragment)
        }

        binding.tvCustomerAddress.text = fetchCurrentAddress()
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