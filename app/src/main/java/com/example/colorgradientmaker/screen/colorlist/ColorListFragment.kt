package com.example.colorgradientmaker.screen.colorlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.colorgradientmaker.R
import com.example.colorgradientmaker.contracts.HasCustomTitle
import com.example.colorgradientmaker.contracts.navigator
import com.example.colorgradientmaker.databinding.FragmentColorListBinding
import com.example.colorgradientmaker.databinding.FragmentCurrentColorBinding

class ColorListFragment: Fragment(), HasCustomTitle {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentColorListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun getTitleRes(): Int = R.string.color_list_title
}