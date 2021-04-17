package com.fixtureservicexyz.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentPlumbingAddingServicesBinding
import com.fixtureservicexyz.models.ProductService
import com.fixtureservicexyz.utils.ShoppingDBTemp
import com.fixtureservicexyz.utils.StrKeys

class PlumbingAddingServicesFragment : Fragment() {

    private lateinit var binding: FragmentPlumbingAddingServicesBinding
    private var productServiceAdding: ProductService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().getParcelable<ProductService>(StrKeys.PLUMBING_SERVICE_SELECTED)?.let {
            productServiceAdding = it
            ShoppingDBTemp.addPlumbingService(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlumbingAddingServicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvPlumbingAddingTitle.text = productServiceAdding?.title
        binding.tvPlumbingAddingUnitPrice.text = "${productServiceAdding?.priceFrom}/unit"
        binding.tvPlumbingAddingUnitAdd.text = "${productServiceAdding?.toBuy} Unit"
        binding.tvPlumbingAddingTotal.text =
            "${productServiceAdding!!.toBuy * productServiceAdding!!.priceFrom}$"

        binding.btnPlumbingAddingAddOne.setOnClickListener {
            productServiceAdding!!.toBuy += 1
            binding.tvPlumbingAddingUnitAdd.text = "${productServiceAdding?.toBuy} Unit"
            binding.tvPlumbingAddingTotal.text =
                "${productServiceAdding!!.toBuy * productServiceAdding!!.priceFrom}$"
            ShoppingDBTemp.addPlumbingService(productServiceAdding!!)
        }
        binding.btnPlumbingAddingLessOne.setOnClickListener {
            if (productServiceAdding!!.toBuy > 1) {
                productServiceAdding!!.toBuy -= 1
                binding.tvPlumbingAddingUnitAdd.text = "${productServiceAdding?.toBuy} Unit"
                binding.tvPlumbingAddingTotal.text =
                    "${productServiceAdding!!.toBuy * productServiceAdding!!.priceFrom}$"
                ShoppingDBTemp.removePlumbingService(productServiceAdding!!)
            }
        }
        binding.ivGoShopping.setOnClickListener {
           findNavController().navigate(R.id.action_plumbingAddingServicesFragment_to_myCartFragment)
        }

        binding.tvPlumbingProceed.setOnClickListener {
            findNavController().navigate(R.id.action_plumbingAddingServicesFragment_to_paymentMethodFragment)
        }
    }
}