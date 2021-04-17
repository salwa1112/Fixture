package com.fixtureservicexyz.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fixtureservicexyz.R
import com.fixtureservicexyz.adapter.ProductServiceCartAdapter
import com.fixtureservicexyz.databinding.FragmentMyCartBinding
import com.fixtureservicexyz.utils.ShoppingDBTemp

class MyCartFragment : Fragment(R.layout.fragment_my_cart),
    DateAndTimePickerFragment.OnDateAndTimeListener {

    private lateinit var binding: FragmentMyCartBinding
    private var selectedData: String? = null


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMyCartBinding.bind(view)

        setUpRecyclerView()

        binding.btnMyCartAddMoreService.setOnClickListener {
            findNavController().navigate(R.id.action_myCartFragment_to_dashboardFragment)
        }

        binding.btnMyCartConfirm.setOnClickListener {
            val bundle = bundleOf("DateConfirmation" to selectedData)
            findNavController().navigate(
                R.id.action_myCartFragment_to_confirmAddressFragment,
                bundle
            )
        }

        binding.tvSelectedDate.setOnClickListener {

            val xx = DateAndTimePickerFragment(this)
            xx.show(requireActivity().supportFragmentManager, "sssss")
        }
    }

    private fun setUpRecyclerView() {
        binding.rvShoppingService.layoutManager = LinearLayoutManager(requireContext())
        binding.rvShoppingService.adapter =
            ProductServiceCartAdapter(ShoppingDBTemp.fetchAllProductsServices())
    }

    override fun DateAndTimeSelectedListener(date: String, time: String) {
        selectedData = "$date $time"
        binding.tvSelectedDate.text = "$date $time"
    }
}