package com.fixtureservicexyz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fixtureservicexyz.R
import com.fixtureservicexyz.adapter.ProductServiceAdapter
import com.fixtureservicexyz.databinding.FragmentElectricServicesBinding
import com.fixtureservicexyz.models.ProductService
import com.fixtureservicexyz.utils.StrKeys
import com.fixtureservicexyz.utils.fetchToSharedPrefs


class ElectricServicesFragment : Fragment(), ProductServiceAdapter.OnProductServiceClickListener  {

    private lateinit var binding:FragmentElectricServicesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentElectricServicesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        binding.tvCustomerAddressElectricServices.text = fetchCurrentAddress()
    }

    private fun setUpRecyclerView(){
        binding.rvElectricService.layoutManager = LinearLayoutManager(requireContext())
        binding.rvElectricService.adapter = ProductServiceAdapter(electricServiceDummyData(),this)

    }
    private fun electricServiceDummyData() = mutableListOf<ProductService>(
        ProductService(
            "electric1", "Ceiling Fan Services",
            listOf("Warranty on parts", "7 days service warranty"), R.drawable.ceiling_fan_services, 20
        ),
        ProductService(
            "electric2", "Exhaust Fan Servicing",
            listOf("Exhaust fan installation", "Installation, Only service charge"), R.drawable.exhaust_fan_servicing, 8
        ),ProductService(
            "electric3", "Main Circuit Breaker (MCB) Services",
            listOf("Install inspection and diagnosis", "Full installation"), R.drawable.main_circuit_breaker, 10
        ),ProductService(
            "electric3", "Drilling Service",
            listOf("", ""), R.drawable.drilling_service, 10
        )
    )

    override fun productServiceClick(serviceSelected: ProductService) {
        val bundle = Bundle()
        bundle.putParcelable(StrKeys.ELECTRIC_SERVICE_SELECTED,serviceSelected)
        findNavController().navigate(R.id.action_electricServicesFragment_to_electricServicingSelectedFragment,bundle)
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