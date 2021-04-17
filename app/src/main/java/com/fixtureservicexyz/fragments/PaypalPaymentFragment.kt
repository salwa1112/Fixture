package com.fixtureservicexyz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentPaypalPaymentBinding

class PaypalPaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaypalPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaypalPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPaypalPay.setOnClickListener {
            if (validateForm())
                findNavController().navigate(R.id.action_paypalPaymentFragment_to_orderResumenFragment)
        }
    }

    private fun validateForm(): Boolean {
        val email = binding.etPaypalEmail.text.toString()
        val password = binding.etPaypalPassword.text.toString()
        var isValidForm = true

        when {
            email.isNullOrEmpty() -> {
                binding.etPaypalEmail.error = "Email is required"
                isValidForm = false
            }
            password.isNullOrEmpty() -> {
                binding.etPaypalPassword.error = "Password is required"
                isValidForm = false
            }
        }

        return isValidForm


    }
}