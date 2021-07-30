package com.example.galery.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Sponsor (

	val id : String,
	val updated_at : String,
	val username : String,
	val name : String,
	val first_name : String,
	val last_name : String,
	val twitter_username : String,
	val portfolio_url : String,
	val bio : String,
	val location : String,
	val links : Links,
	val profile_image : Profile_image,
	val instagram_username : String,
	val total_collections : Int,
	val total_likes : Int,
	val total_photos : Int,
	val accepted_tos : Boolean,
	val for_hire : Boolean,
	val social : Social
): Parcelable