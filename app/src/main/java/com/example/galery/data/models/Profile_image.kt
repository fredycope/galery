package com.example.galery.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Profile_image (

	val small : String,
	val medium : String,
	val large : String
): Parcelable