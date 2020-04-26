package com.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statement (
    val title: String,
    val desc: String,
    val date: String,
    val value: Double

): Parcelable
