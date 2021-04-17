package com.fixtureservicexyz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentCardsPaymentsBinding
import com.fixtureservicexyz.utils.StrKeys


class CardsPaymentsFragment : Fragment(R.layout.fragment_cards_payments) {

    private lateinit var binding: FragmentCardsPaymentsBinding
    private lateinit var paymentMethod: String
    private var isCardTypedClicked:Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCardsPaymentsBinding.bind(view)

        resetSelectedPaymentCard()

        arguments?.getString(StrKeys.PAYMENT_METHOD).let {
            paymentMethod = it!!
        }

        binding.btnCardsPayments.setOnClickListener {
            if(validateForm())
                findNavController().navigate(R.id.action_cardsPaymentsFragment_to_orderResumenFragment)
        }

        binding.btnVisa.setOnClickListener {
            binding.btnVisa.setBackgroundResource(R.drawable.btn_credit_card_payment_selected_shape)
            binding.btnVisa.setTextColor(resources.getColor(android.R.color.white))

            binding.btnMasterCard.setBackgroundResource(R.drawable.btn_credit_card_payment_shape)
            binding.btnMasterCard.setTextColor(resources.getColor(R.color.colorAccent))

            binding.btnAmex.setBackgroundResource(R.drawable.btn_credit_card_payment_shape)
            binding.btnAmex.setTextColor(resources.getColor(R.color.colorAccent))

            isCardTypedClicked = true
        }

        binding.btnMasterCard.setOnClickListener {
            binding.btnVisa.setBackgroundResource(R.drawable.btn_credit_card_payment_shape)
            binding.btnVisa.setTextColor(resources.getColor(R.color.colorAccent))

            binding.btnMasterCard.setBackgroundResource(R.drawable.btn_credit_card_payment_selected_shape)
            binding.btnMasterCard.setTextColor(resources.getColor(android.R.color.white))

            binding.btnAmex.setBackgroundResource(R.drawable.btn_credit_card_payment_shape)
            binding.btnAmex.setTextColor(resources.getColor(R.color.colorAccent))

            isCardTypedClicked = true
        }

        binding.btnAmex.setOnClickListener {
            binding.btnVisa.setBackgroundResource(R.drawable.btn_credit_card_payment_shape)
            binding.btnVisa.setTextColor(resources.getColor(R.color.colorAccent))

            binding.btnMasterCard.setBackgroundResource(R.drawable.btn_credit_card_payment_shape)
            binding.btnMasterCard.setTextColor(resources.getColor(R.color.colorAccent))

            binding.btnAmex.setBackgroundResource(R.drawable.btn_credit_card_payment_selected_shape)
            binding.btnAmex.setTextColor(resources.getColor(android.R.color.white))

            isCardTypedClicked = true
        }

    }

    private fun resetSelectedPaymentCard() {
        with(binding) {
            btnVisa.setBackgroundResource(R.drawable.btn_credit_card_payment_shape)
            btnMasterCard.setBackgroundResource(R.drawable.btn_credit_card_payment_shape)
            btnAmex.setBackgroundResource(R.drawable.btn_credit_card_payment_shape)
        }
    }

    private fun validateForm(): Boolean {

        val creditCardNumber = binding.etCreditCardNumber.text.toString()
        var isValidForm = true

        when {
            !isCardTypedClicked ->{
                Toast.makeText(
                    requireActivity(),
                    "You must select a credit card type",
                    Toast.LENGTH_SHORT
                ).show()
                isValidForm = false
            }

            creditCardNumber.isNullOrEmpty() || creditCardNumber.trim().length < 16 || creditCardNumber.trim().length > 16 -> {
                binding.etCreditCardNumber.error = "Enter a valid number card"
                isValidForm = false
            }
        }

        return isValidForm
    }
}