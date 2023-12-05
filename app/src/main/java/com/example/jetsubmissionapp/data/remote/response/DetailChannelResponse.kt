package com.example.jetsubmissionapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailChannelResponse(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("banner")
	val banner: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("english_name")
	val englishName: String? = null,

	@field:SerializedName("yt_uploads_id")
	val ytUploadsId: String? = null,

	@field:SerializedName("inactive")
	val inactive: Boolean? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("subscriber_count")
	val subscriberCount: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("video_count")
	val videoCount: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("view_count")
	val viewCount: String? = null,

	@field:SerializedName("yt_handle")
	val ytHandle: List<String>? = null,

	@field:SerializedName("org")
	val org: String? = null,

	@field:SerializedName("group")
	val group: String? = null,
)
