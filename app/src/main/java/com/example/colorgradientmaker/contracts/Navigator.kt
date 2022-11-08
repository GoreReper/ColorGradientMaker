package com.example.colorgradientmaker.contracts

import androidx.fragment.app.Fragment

interface Navigator {

    fun showColorListScreen()

    fun showSizeListScreen()

    fun goBack()

    fun goToMainScreen()

}

fun Fragment.navigator(): Navigator{
    return requireActivity() as Navigator
}