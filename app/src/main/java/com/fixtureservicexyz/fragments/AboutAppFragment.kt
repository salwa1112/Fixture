package com.fixtureservicexyz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.FragmentAboutAppBinding


class AboutAppFragment : Fragment(R.layout.fragment_about_app) {
    private lateinit var binding:FragmentAboutAppBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutAppBinding.bind(view)
    }
}