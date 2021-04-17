package com.fixtureservicexyz.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentPlumbingServicingSelectedBinding
import com.fixtureservicexyz.models.ProductService
import com.fixtureservicexyz.utils.StrKeys


class PlumbingServicingSelectedFragment : Fragment() {

    private var productServiceSelected:ProductService? =null

    private lateinit var binding:FragmentPlumbingServicingSelectedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().getParcelable<ProductService>(StrKeys.PLUMBING_SERVICE_SELECTED)?.let {
            productServiceSelected = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlumbingServicingSelectedBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvPlumbingServiceTitleSelected.text = productServiceSelected?.title
        binding.tvPlumbingServiceUnitPriceSelected.text = "${productServiceSelected?.priceFrom}/unit"
        binding.btnPlumbingServiceAddSelected.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(StrKeys.PLUMBING_SERVICE_SELECTED,productServiceSelected)
            findNavController().navigate(R.id.action_plumbingServicingSelectedFragment_to_plumbingAddingServicesFragment,bundle)
        }
    }
}