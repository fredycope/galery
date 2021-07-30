package com.example.galery.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Social (

	val instagram_username : String,
	val portfolio_url : String,
	val twitter_username : String,
	val paypal_email : String
): Parcelable