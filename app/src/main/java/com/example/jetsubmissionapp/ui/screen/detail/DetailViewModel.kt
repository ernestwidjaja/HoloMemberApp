package com.example.jetsubmissionapp.ui.screen.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.jetsubmissionapp.data.ChannelRepository
import com.example.jetsubmissionapp.data.remote.response.ChannelsResponseItem
import com.example.jetsubmissionapp.data.remote.response.DetailChannelResponse
import com.example.jetsubmissionapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ChannelRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<DetailChannelResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiState: StateFlow<UiState<DetailChannelResponse>>
        get() = _uiState

    fun getMemberDetail(id: String) {
        viewModelScope.launch {
            repository.getMemberDetail(id)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = it
                }
        }
    }

    private val membersData = MutableLiveData<ChannelsResponseItem>()

    fun setMembersData(favoriteMembersEntity: ChannelsResponseItem) {
        membersData.value = favoriteMembersEntity
    }

    val favoriteStatus = membersData.switchMap {
        it.name?.let { it1 -> repository.isFavorite(it1) }
    }

    fun changeFavorite(favoriteMembersEntity: ChannelsResponseItem) {
        viewModelScope.launch {
            if (favoriteStatus.value as Boolean) {
                repository.delete(favoriteMembersEntity)
            } else {
                repository.insert(favoriteMembersEntity)
            }
        }
    }
}