package com.example.galery.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links (

	val self : String,
	val html : String,
	val photos : String,
	val likes : String,
	val portfolio : String,
	val following : String,
	val followers : String
): Parcelable