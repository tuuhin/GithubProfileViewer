package com.eva.githubprofileviewer.presentation.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.R
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.utils.toCompactNumber

@Composable
fun RepositoryAssociatedIconsInfo(
    repositoryModel: GitHubRepositoryModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp)
    ) {
        AssociatedIcons(
            label = "Forks",
            count = repositoryModel.forksCount,
            icon = R.drawable.git_fork
        )
        AssociatedIcons(
            label = "Stars",
            count = repositoryModel.starsCount,
            icon = R.drawable.star_shape
        )
        AssociatedIcons(
            label = "Issues",
            count = repositoryModel.issuesCount,
            icon = R.drawable.message
        )
    }
}


@Composable
fun AssociatedIcons(
    @DrawableRes icon: Int,
    count: Int,
    label: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        Icon(painterResource(id = icon), contentDescription = contentDescription)
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = count.toCompactNumber(), style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, style = MaterialTheme.typography.labelLarge)
        }
    }

}