package com.example.jetsubmissionapp.data.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "members")
data class ChannelsResponseItem(
	@PrimaryKey
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("english_name")
	val englishName: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,
)
