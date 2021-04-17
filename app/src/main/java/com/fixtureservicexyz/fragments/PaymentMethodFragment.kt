package com.fixtureservicexyz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentPaymentMethodBinding
import com.fixtureservicexyz.utils.StrKeys


class PaymentMethodFragment : Fragment() {

    private lateinit var binding:FragmentPaymentMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentMethodBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvCreditCardPayment.setOnClickListener {
            val bundle = bundleOf(StrKeys.PAYMENT_METHOD to StrKeys.CREDIT_CARD_PAYMENT)
            findNavController().navigate(R.id.action_paymentMethodFragment_to_cardsPaymentsFragment,bundle)
        }

        binding.cvDebitCardPayment.setOnClickListener {
            val bundle = bundleOf(StrKeys.PAYMENT_METHOD to StrKeys.DEBIT_CARD_PAYMENT)


            findNavController().navigate(R.id.action_paymentMethodFragment_to_cardsPaymentsFragment,bundle)
        }

        binding.cvApplePayPayment.setOnClickListener {
            findNavController().navigate(R.id.action_paymentMethodFragment_to_applePayFragment)
        }

        binding.cvCashDelivery.setOnClickListener {
            findNavController().navigate(R.id.action_paymentMethodFragment_to_debitCardPaymentFragment)
        }

        binding.cvPaypalPayment.setOnClickListener {
            findNavController().navigate(R.id.action_paymentMethodFragment_to_paypalPaymentFragment)
        }

    }
}