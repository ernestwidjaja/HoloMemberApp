package com.example.jetsubmissionapp.di

import android.content.Context
import com.example.jetsubmissionapp.data.ChannelRepository
import com.example.jetsubmissionapp.data.local.database.MembersDatabase
import com.example.jetsubmissionapp.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): ChannelRepository {
        val apiService = ApiConfig.getApiService()
        val database = MembersDatabase.getInstance(context)
        val dao = database.membersDao()
        return ChannelRepository.getInstance(apiService, dao)
    }
}