package uz.coder.d2lesson114retrofit.mod

import com.google.gson.annotations.SerializedName

data class News(

	@field:SerializedName("RateLimit")
	val rateLimit: RateLimit? = null,

	@field:SerializedName("Type")
	val type: Int? = null,

	@field:SerializedName("Message")
	val message: String? = null,

	@field:SerializedName("Promoted")
	val promoted: List<Any?>? = null,

	@field:SerializedName("HasWarning")
	val hasWarning: Boolean? = null,

	@field:SerializedName("Data")
	val data: List<DataItem?>? = null
)