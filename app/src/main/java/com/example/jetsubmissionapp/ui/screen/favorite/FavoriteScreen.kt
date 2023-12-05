package com.example.jetsubmissionapp.ui.screen.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetsubmissionapp.ui.ViewModelFactory
import com.example.jetsubmissionapp.ui.components.VerticalGrid

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    viewModel.getMembersFavorite().collectAsState(emptyList()).value.let {
        VerticalGrid(
            navigateToDetail = navigateToDetail,
            listMember = it,
            modifier = modifier
        )
    }
}