package com.eva.githubprofileviewer.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.GitHubUserModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubUserSocialDataCard(
    label: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label.uppercase(), style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = count.toString(), style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun GithubUserSocialData(
    model:GitHubUserModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth().padding(2.dp)
    ) {
        GithubUserSocialDataCard(label = "Repositories", count = model.repoCount)
        GithubUserSocialDataCard(label = "Followers", count = model.followerCount)
        GithubUserSocialDataCard(label = "Following", count = model.followingCount)
    }
}