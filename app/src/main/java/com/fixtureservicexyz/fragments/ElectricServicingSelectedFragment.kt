package com.fixtureservicexyz.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentElectricServicingSelectedBinding
import com.fixtureservicexyz.models.ProductService
import com.fixtureservicexyz.utils.StrKeys

class ElectricServicingSelectedFragment : Fragment() {

    private lateinit var binding:FragmentElectricServicingSelectedBinding

    private var productServiceSelected: ProductService? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().getParcelable<ProductService>(StrKeys.ELECTRIC_SERVICE_SELECTED)?.let {
            productServiceSelected = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentElectricServicingSelectedBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvElectricServiceTitleSelected.text = productServiceSelected?.title
        binding.tvElectricServiceUnitPriceSelected.text = "${productServiceSelected?.priceFrom}/unit"
        binding.btnElectricServiceAddSelected.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(StrKeys.ELECTRIC_SERVICE_SELECTED,productServiceSelected)
            findNavController().navigate(R.id.action_electricServicingSelectedFragment_to_electricAddingServicesFragment,bundle)
        }
    }
}