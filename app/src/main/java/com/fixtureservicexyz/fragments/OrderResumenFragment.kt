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
import com.fixtureservicexyz.adapter.ProductServiceOrderConfirmationAdapter
import com.fixtureservicexyz.databinding.FragmentOrderResumenBinding
import com.fixtureservicexyz.utils.ShoppingDBTemp
import com.fixtureservicexyz.utils.StrKeys
import com.fixtureservicexyz.utils.fetchToSharedPrefs


class OrderResumenFragment : Fragment() {

    private lateinit var binding:FragmentOrderResumenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderResumenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        binding.btnResumeConfirm.setOnClickListener {
            findNavController().navigate(R.id.action_orderResumenFragment_to_endOrderFragment)
        }

        binding.tvCustomerAddressOrderResume.text = fetchCurrentAddress()
    }
    @SuppressLint("SetTextI18n")
    private fun setUpRecyclerView() {
        val list = ShoppingDBTemp.fetchAllProductsServices()
        binding.rvOrderResume.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrderResume.adapter = ProductServiceOrderConfirmationAdapter(list)

        var costServices = 0
        var totalOrder = 0
        list.forEach {
            costServices += it.toBuy * it.priceFrom
        }

        totalOrder = costServices + 10 //10 Delivery Free

        binding.tvResumeCost.text = "${costServices}$ "
        binding.tvResumeTotal.text = "${totalOrder}$"
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