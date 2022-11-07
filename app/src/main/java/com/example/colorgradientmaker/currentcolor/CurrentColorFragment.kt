package com.example.colorgradientmaker.currentcolor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import com.example.colorgradientmaker.R
import com.example.colorgradientmaker.databinding.FragmentCurrentColorBinding
import com.example.colorgradientmaker.model.color.NamedColor
import com.example.colorgradientmaker.model.size.NamedSize
import kotlin.math.abs
import kotlin.random.Random


class CurrentColorFragment: Fragment() {

    private lateinit var binding: FragmentCurrentColorBinding
    lateinit var currentColor: NamedColor
    lateinit var currentSize: NamedSize

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentColor = arguments?.getParcelable(ARG_COLOR) ?: generateColor()
        currentSize = arguments?.getParcelable(ARG_SIZE)?: generateSize()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentColorBinding.inflate(inflater, container, false)
        renderGradient(currentColor,currentSize)

        binding.generateColorsButton.setOnClickListener{
            currentColor = generateColor()
            currentSize = generateSize()
            renderGradient(currentColor,currentSize)
        }




        return binding.root
    }

    private fun generateColor(): NamedColor {
        return NamedColor(value = -Random.nextInt(0xFFFFFF))
    }

    private fun generateSize(): NamedSize {
        val randSize = Random.nextInt(5, 20)
        return NamedSize(value = randSize + (1 - randSize % 2))
    }

    private fun generateOpacity(size: NamedSize): List<Int> {
        val center = size.value/2
        val n = 128/center + 1
        return (0 until size.value * size.value).map {
            val row = (it / size.value)
            val column = (it % size.value)

            255 - abs(center-column)*n- abs(center-row)*n
        }
    }

    private fun renderGradient(color: NamedColor, size: NamedSize) = with(binding) {
        colorsContainer.removeAllViews()
        val opacities = generateOpacity(size)
        val identifiers = opacities.indices.map { View.generateViewId() }
        for (i in opacities.indices) {
            val row = i / size.value
            val column = i % size.value

            val view = View(requireActivity())
            view.setBackgroundColor(color.value)
            view.alpha = (opacities[i] / 255.0).toFloat()

            view.id = identifiers[i]

            val params = ConstraintLayout.LayoutParams(0, 0)
            params.setMargins(resources.getDimensionPixelSize(R.dimen.space))
            view.layoutParams = params

            // startToX constraint
            if (column == 0) params.startToStart =
                ConstraintLayout.LayoutParams.PARENT_ID
            else params.startToEnd = identifiers[i - 1]

            // endToX constraint
            if (column == size.value - 1) params.endToEnd =
                ConstraintLayout.LayoutParams.PARENT_ID
            else params.endToStart = identifiers[i + 1]

            // topToX constraint
            if (row == 0) params.topToTop =
                ConstraintLayout.LayoutParams.PARENT_ID
            else params.topToBottom = identifiers[i - size.value]

            // bottomToX constraint
            if (row == size.value - 1) params.bottomToBottom =
                ConstraintLayout.LayoutParams.PARENT_ID
            else params.bottomToTop = identifiers[i + size.value]

            colorsContainer.addView(view)

        }

    }

    companion object {
        @JvmStatic
        private val ARG_COLOR = "ARG_COLOR"
        @JvmStatic
        private val ARG_SIZE= "ARG_SIZE"

        fun newInstance(color: NamedColor, size: NamedSize):CurrentColorFragment {
            val args = Bundle()
            args.putParcelable(ARG_COLOR, color)
            args.putParcelable(ARG_SIZE, size)
            val fragment = CurrentColorFragment()
            fragment.arguments = args
            return fragment
        }
    }
}


