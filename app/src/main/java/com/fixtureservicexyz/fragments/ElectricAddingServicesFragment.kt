package com.fixtureservicexyz.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentElectricAddingServicesBinding
import com.fixtureservicexyz.models.ProductService
import com.fixtureservicexyz.utils.ShoppingDBTemp
import com.fixtureservicexyz.utils.StrKeys

class ElectricAddingServicesFragment : Fragment() {

    private lateinit var binding: FragmentElectricAddingServicesBinding
    private var productServiceAdding: ProductService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().getParcelable<ProductService>(StrKeys.ELECTRIC_SERVICE_SELECTED)?.let {
            productServiceAdding = it
            ShoppingDBTemp.addPlumbingService(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentElectricAddingServicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvElectricAddingTitle.text = productServiceAdding?.title
        binding.tvElectricAddingUnitPrice.text = "${productServiceAdding?.priceFrom}/unit"
        binding.tvElectricAddingUnitAdd.text = "${productServiceAdding?.toBuy} Unit"
        binding.tvElectricAddingTotal.text =
            "${productServiceAdding!!.toBuy * productServiceAdding!!.priceFrom}$"

        binding.btnElectricAddingAddOne.setOnClickListener {
            productServiceAdding!!.toBuy += 1
            binding.tvElectricAddingUnitAdd.text = "${productServiceAdding?.toBuy} Unit"
            binding.tvElectricAddingTotal.text =
                "${productServiceAdding!!.toBuy * productServiceAdding!!.priceFrom}$"
            ShoppingDBTemp.addPlumbingService(productServiceAdding!!)
        }
        binding.btnElectricAddingLessOne.setOnClickListener {
            if (productServiceAdding!!.toBuy > 1) {
                productServiceAdding!!.toBuy -= 1
                binding.tvElectricAddingUnitAdd.text = "${productServiceAdding?.toBuy} Unit"
                binding.tvElectricAddingTotal.text =
                    "${productServiceAdding!!.toBuy * productServiceAdding!!.priceFrom}$"
                ShoppingDBTemp.removePlumbingService(productServiceAdding!!)
            }
        }
        binding.ivElectricGoShopping.setOnClickListener {
            findNavController().navigate(R.id.action_electricAddingServicesFragment_to_myCartFragment)
        }

        binding.tvElectricProceed.setOnClickListener {
            findNavController().navigate(R.id.action_electricAddingServicesFragment_to_paymentMethodFragment)
        }
    }
}