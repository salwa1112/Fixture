package com.fixtureservicexyz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentApplePayBinding


class ApplePayFragment : Fragment(R.layout.fragment_apple_pay) {

    private lateinit var binding: FragmentApplePayBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentApplePayBinding.bind(view)

        binding.ivFingerprintIdentity.setOnClickListener {
            findNavController().navigate(R.id.action_applePayFragment_to_orderResumenFragment)
        }
    }

}