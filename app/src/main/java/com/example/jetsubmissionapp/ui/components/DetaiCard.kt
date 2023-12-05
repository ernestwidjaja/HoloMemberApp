package com.example.jetsubmissionapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetsubmissionapp.R

@Composable
fun DetailCard(
    status: Boolean?,
    group: String?,
    org: String?,
    subs: String?,
    videos: String?,
    views: String?,
    description: String?,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            Arrangement.spacedBy(4.dp)
        ) {
            Text(
                stringResource(R.string.profile),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (status == true) {
                Text(stringResource(R.string.status_inactive))
            } else {
                Text(stringResource(R.string.status_active))
            }
            Text(stringResource(R.string.agency, org.toString()))
            Text(stringResource(R.string.group, group.toString()))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                stringResource(R.string.description),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (description.isNullOrEmpty()) {
                Text(stringResource(R.string.no_data))
            } else {
                Text("$description")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                stringResource(R.string.statistic),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(stringResource(R.string.subscriber, subs.toString()))
            Text(stringResource(R.string.videos, videos.toString()))
            Text(stringResource(R.string.views, views.toString()))
        }
    }
}