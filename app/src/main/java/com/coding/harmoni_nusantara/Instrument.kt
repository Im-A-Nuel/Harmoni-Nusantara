package com.coding.harmoni_nusantara

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Instrument (
    val name: String,
    val description: String,
    val photo: Int
): Parcelable