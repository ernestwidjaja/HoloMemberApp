package com.example.jetsubmissionapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetsubmissionapp.data.remote.response.ChannelsResponseItem
import com.example.jetsubmissionapp.ui.ViewModelFactory
import com.example.jetsubmissionapp.ui.common.UiState
import com.example.jetsubmissionapp.ui.components.Error
import com.example.jetsubmissionapp.ui.components.SearchBar
import com.example.jetsubmissionapp.ui.components.VerticalGrid

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    navigateToDetail: (String) -> Unit,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(
                    Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center))
                viewModel.getAllMembers()
            }

            is UiState.Success -> {
                HomeContent(
                    listMember = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    query = query,
                    onQueryChange = viewModel::searchMember
                )
            }

            is UiState.Error -> {
                Error(message = uiState.errorMessage)
            }
        }
    }
}

@Composable
fun HomeContent(
    listMember: List<ChannelsResponseItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
        )
        VerticalGrid(
            navigateToDetail = navigateToDetail,
            listMember = listMember,
        )
    }
}