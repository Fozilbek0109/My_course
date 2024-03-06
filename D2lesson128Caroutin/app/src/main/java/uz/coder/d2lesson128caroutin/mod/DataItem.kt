package uz.coder.d2lesson114retrofit.mod

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("upvotes")
	val upvotes: String? = null,

	@field:SerializedName("published_on")
	val publishedOn: Int? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("downvotes")
	val downvotes: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("tags")
	val tags: String? = null,

	@field:SerializedName("imageurl")
	val imageurl: String? = null,

	@field:SerializedName("guid")
	val guid: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("categories")
	val categories: String? = null,

	@field:SerializedName("lang")
	val lang: String? = null,

	@field:SerializedName("source_info")
	val sourceInfo: SourceInfo? = null
)