package com.example.galery.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Sponsorship (

	val impression_urls : List<String>,
	val tagline : String,
	val tagline_url : String,
	val sponsor : Sponsor
): Parcelable