package uz.coder.d2lesson128caroutin.model

import com.google.gson.annotations.SerializedName

data class Company(

	@field:SerializedName("bs")
	val bs: String? = null,

	@field:SerializedName("catchPhrase")
	val catchPhrase: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)