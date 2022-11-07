package com.example.colorgradientmaker.model.size

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NamedSize(
    val id: Long = 0,
    val name: String = "",
    val value: Int
) : Parcelable