package com.example.jetsubmissionapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetsubmissionapp.data.ChannelRepository
import com.example.jetsubmissionapp.data.remote.response.ChannelsResponseItem
import com.example.jetsubmissionapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ChannelRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<ChannelsResponseItem>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ChannelsResponseItem>>>
        get() = _uiState

    fun getAllMembers() {
        viewModelScope.launch {
            repository.getAllMembers()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = it
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchMember(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchMembers(_query.value)
                .collect {
                    _uiState.value = it
                }
        }
    }
}