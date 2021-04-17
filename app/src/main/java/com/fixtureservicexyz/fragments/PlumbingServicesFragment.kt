package com.fixtureservicexyz.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fixtureservicexyz.R
import com.fixtureservicexyz.adapter.ProductServiceAdapter
import com.fixtureservicexyz.databinding.FragmentPlumbingServicesBinding
import com.fixtureservicexyz.models.ProductService
import com.fixtureservicexyz.utils.StrKeys
import com.fixtureservicexyz.utils.fetchToSharedPrefs


class PlumbingServicesFragment : Fragment(), ProductServiceAdapter.OnProductServiceClickListener {

    private lateinit var binding:FragmentPlumbingServicesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlumbingServicesBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        binding.tvPlumbingServicesFounds.text = "${plumbingServiceDummyData().size} service(s) in Plumbing"
        binding.tvCustomerAddressPlumbingService.text = fetchCurrentAddress()
    }

    private fun setUpRecyclerView() {
        binding.rvPlumbingService.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPlumbingService.adapter = ProductServiceAdapter(plumbingServiceDummyData(),this)

    }

    private fun plumbingServiceDummyData() = mutableListOf<ProductService>(
        ProductService(
            "ps1", "Water Meter Servicing",
            listOf("Repair and maintenance", "Installation, repair and maintenance"), R.drawable.water_meter_servicing, 20
        ),
        ProductService(
            "ps2", "Water Tap Servicing",
            listOf("Water tap installation", "Water tap repair services"), R.drawable.water_tap_services, 10
        ),
        ProductService(
            "ps3", "Sink Repair",
            listOf("Installation and repair", "Blockage removal"), R.drawable.sink_repair, 10
        ),
        ProductService(
            "ps4", "Plumbing Check Up",
            listOf("ONLY problem identification", "background checked professional plumbers"), R.drawable.plumbing_check_up, 10
        )
    )

    override fun productServiceClick(serviceSelected: ProductService) {
        val bundle = Bundle()
        bundle.putParcelable(StrKeys.PLUMBING_SERVICE_SELECTED,serviceSelected)
        findNavController().navigate(R.id.action_plumbingServicesFragment_to_plumbingServicingSelectedFragment,bundle)
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