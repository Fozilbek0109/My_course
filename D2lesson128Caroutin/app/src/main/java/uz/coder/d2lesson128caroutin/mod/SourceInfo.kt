package uz.coder.d2lesson114retrofit.mod

import com.google.gson.annotations.SerializedName

data class SourceInfo(

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("lang")
	val lang: String? = null
)