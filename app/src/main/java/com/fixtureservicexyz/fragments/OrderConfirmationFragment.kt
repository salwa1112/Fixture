package com.fixtureservicexyz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fixtureservicexyz.R
import com.fixtureservicexyz.adapter.ProductServiceOrderConfirmationAdapter
import com.fixtureservicexyz.databinding.FragmentOrderConfirmationBinding
import com.fixtureservicexyz.utils.ShoppingDBTemp
import com.fixtureservicexyz.utils.StrKeys
import com.fixtureservicexyz.utils.fetchToSharedPrefs


class OrderConfirmationFragment : Fragment() {

    private lateinit var binding:FragmentOrderConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderConfirmationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        binding.btnOrderConfirm.setOnClickListener {
            findNavController().navigate(R.id.action_orderConfirmationFragment_to_paymentMethodFragment)
        }
        binding.tvCustomerAddressOrderConfirmation.text = fetchCurrentAddress()
    }

    private fun setUpRecyclerView() {
        val list = ShoppingDBTemp.fetchAllProductsServices()
        binding.rvOrderConfirmation.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrderConfirmation.adapter = ProductServiceOrderConfirmationAdapter(list)

        var costServices = 0
        var totalOrder = 0
        list.forEach {
            costServices += it.toBuy * it.priceFrom
        }

        totalOrder = costServices + 10 //10 Delivery Free

        binding.tvServicesCost.text = "${costServices}$ "
        binding.tvTotalOrder.text = "${totalOrder}$"
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