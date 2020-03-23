package com.example.studentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal(
    val portalTitle: String,
    val portalUrl: String
) : Parcelable