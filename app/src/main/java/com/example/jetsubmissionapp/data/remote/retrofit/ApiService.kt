package com.example.jetsubmissionapp.data.remote.retrofit

import com.example.jetsubmissionapp.data.remote.response.ChannelsResponseItem
import com.example.jetsubmissionapp.data.remote.response.DetailChannelResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("channels/")
    suspend fun getAllMember(
        @Query("type") type: String = "vtuber",
        @Query("limit") limit: Int = 100,
        @Query("org") org: String = "Hololive",
        @Query("sort") sort: String = "group"
    ): List<ChannelsResponseItem>

    @GET("channels/{channelId}")
    suspend fun getDetailMember(
        @Path("channelId") channelId: String
    ): DetailChannelResponse
}