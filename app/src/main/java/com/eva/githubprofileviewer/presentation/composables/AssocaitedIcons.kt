package com.eva.githubprofileviewer.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.outlined.ForkRight
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.utils.toCompactNumber

@Composable
fun RepositoryAssociatedIconsInfo(
    repositoryModel:GitHubRepositoryModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp)
    ) {
        AssociatedIcons(
            icon = Icons.Outlined.ForkRight,
            label = "Forks",
            count = repositoryModel.forksCount
        )
        AssociatedIcons(
            icon = Icons.Filled.Star,
            label = "Stars",
            count = repositoryModel.starsCount,
        )
        AssociatedIcons(
            icon = Icons.Outlined.Message,
            label = "Issues",
            count = repositoryModel.issuesCount
        )
    }
}

@Composable
fun AssociatedIcons(
    icon: ImageVector,
    count: Int,
    label: String,
    modifier: Modifier = Modifier,
    contentDescription: String = "Icons description",
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        Icon(icon, contentDescription)
        Column(
            modifier = Modifier.padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = count.toCompactNumber())
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, style = MaterialTheme.typography.bodySmall)
        }
    }

}