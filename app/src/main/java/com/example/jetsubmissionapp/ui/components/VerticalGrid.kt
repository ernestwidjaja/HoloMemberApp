package com.example.jetsubmissionapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetsubmissionapp.R
import com.example.jetsubmissionapp.data.remote.response.ChannelsResponseItem

@Composable
fun VerticalGrid(
    navigateToDetail: (String) -> Unit,
    listMember: List<ChannelsResponseItem>,
    modifier: Modifier = Modifier
) {
    if (listMember.isEmpty()) {
        Error(message = stringResource(R.string.no_data))
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.testTag("MemberList")
    ) {
        items(listMember) {
            MemberItem(
                channelName = it.name,
                name = it.englishName,
                photoUrl = it.photo,
                modifier.clickable {
                    navigateToDetail(it.id)
                }
            )
        }
    }
}