package com.example.colorgradientmaker.model.color

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class NamedColor(
    val id: Long = 0,
    val name: String = "",
    val value: Int
): Parcelable