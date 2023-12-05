package com.example.jetsubmissionapp.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.jetsubmissionapp.R
import com.example.jetsubmissionapp.data.remote.response.ChannelsResponseItem
import com.example.jetsubmissionapp.data.remote.response.DetailChannelResponse
import com.example.jetsubmissionapp.ui.ViewModelFactory
import com.example.jetsubmissionapp.ui.common.UiState
import com.example.jetsubmissionapp.ui.components.DetailCard
import com.example.jetsubmissionapp.ui.components.DetailProfile
import com.example.jetsubmissionapp.ui.components.Error

@Composable
fun DetailScreen(
    memberId: String,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(
                    Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
                viewModel.getMemberDetail(memberId)
            }

            is UiState.Success -> {
                val entity = ChannelsResponseItem(
                    uiState.data.id.toString(),
                    uiState.data.name,
                    uiState.data.englishName,
                    uiState.data.photo.toString()
                )
                viewModel.setMembersData(entity)
                val favoriteStatus by viewModel.favoriteStatus.observeAsState(false)
                DetailContent(
                    data = uiState.data,
                    onBackClick = navigateBack,
                    favoriteStatus = favoriteStatus,
                    updateFavoriteStatus = {
                        viewModel.changeFavorite(entity)
                    },
                )
            }

            is UiState.Error -> {
                Error(message = uiState.errorMessage)
            }
        }
    }
}

@Composable
fun DetailContent(
    data: DetailChannelResponse,
    onBackClick: () -> Unit,
    favoriteStatus: Boolean,
    updateFavoriteStatus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    updateFavoriteStatus()
                },
            ) {
                Icon(
                    imageVector = if (favoriteStatus) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = stringResource(R.string.add_to_favorite),
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                AsyncImage(
                    model = data.banner,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            DetailProfile(
                channelName = data.name,
                name = data.englishName,
                photoUrl = data.photo,
                channelId = data.ytHandle?.get(0),
            )
            DetailCard(
                status = data.inactive,
                group = data.group,
                org = data.org,
                subs = data.subscriberCount,
                videos = data.videoCount,
                views = data.viewCount,
                description = data.description
            )
        }
    }
}