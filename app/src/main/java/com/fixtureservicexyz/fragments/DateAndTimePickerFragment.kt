package com.fixtureservicexyz.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.fixtureservicexyz.databinding.FragmentDataAndTimePickerBinding
import java.util.*

class DateAndTimePickerFragment(private val listener:OnDateAndTimeListener) : DialogFragment() {

    private lateinit var binding: FragmentDataAndTimePickerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataAndTimePickerBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tpTime.setIs24HourView(true)


        binding.fabSend.setOnClickListener {
            //val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm")
            val year = binding.dpDate.year
            val month = binding.dpDate.month
            val day = binding.dpDate.dayOfMonth

            val hours = binding.tpTime.hour
            val minute = binding.tpTime.minute

            val date = "$month/$day/$year"
            val time = "$hours:$minute"

            listener.DateAndTimeSelectedListener(date,time)
            this.dismiss()
        }
    }

    interface OnDateAndTimeListener {
        fun DateAndTimeSelectedListener(date: String, time: String)
    }
}