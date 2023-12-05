package com.example.jetsubmissionapp.ui.screen.favorite

import androidx.lifecycle.ViewModel
import com.example.jetsubmissionapp.data.ChannelRepository

class FavoriteViewModel(
    private val repository: ChannelRepository
) : ViewModel() {
    fun getMembersFavorite() =
        repository.getMembersFavorite()
}