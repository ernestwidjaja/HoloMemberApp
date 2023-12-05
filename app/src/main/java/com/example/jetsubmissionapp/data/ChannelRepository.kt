package com.example.jetsubmissionapp.data

import androidx.lifecycle.LiveData
import com.example.jetsubmissionapp.data.local.database.MembersDao
import com.example.jetsubmissionapp.data.remote.response.ChannelsResponseItem
import com.example.jetsubmissionapp.data.remote.retrofit.ApiService
import com.example.jetsubmissionapp.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class ChannelRepository(
    private val apiService: ApiService,
    private val membersDao: MembersDao
) {
    private var data: List<ChannelsResponseItem>? = null

    suspend fun getAllMembers() = flow {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.getAllMember()
            data = successResponse
            emit(UiState.Success(successResponse))
        } catch (e: HttpException) {
            emit(UiState.Error(e.message.toString()))
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
        }
    }

    suspend fun getMemberDetail(id: String) = flow {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.getDetailMember(id)
            emit(UiState.Success(successResponse))
        } catch (e: HttpException) {
            emit(UiState.Error(e.message.toString()))
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
        }
    }

    fun searchMembers(query: String) = flow {
        emit(UiState.Loading)
        val searchResult = data!!.filter {
            it.name.toString().contains(query, ignoreCase = true)
            it.englishName.toString().contains(query, ignoreCase = true)
        }
        emit(UiState.Success(searchResult))
    }

    suspend fun insert(entity: ChannelsResponseItem) {
        membersDao.insert(entity)
    }

    suspend fun delete(favoriteMembersEntity: ChannelsResponseItem) {
        membersDao.delete(favoriteMembersEntity)
    }

    fun isFavorite(name: String): LiveData<Boolean> {
        return membersDao.isFavorite(name)
    }

    fun getMembersFavorite(): Flow<List<ChannelsResponseItem>> {
        return membersDao.getFavoriteUser()
    }

    companion object {
        @Volatile
        private var instance: ChannelRepository? = null
        fun getInstance(
            apiService: ApiService,
            membersDao: MembersDao
        ): ChannelRepository =
            instance ?: synchronized(this) {
                instance ?: ChannelRepository(apiService, membersDao)
            }.also { instance = it }
    }
}