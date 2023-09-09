package com.eva.githubprofileviewer.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.R
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.presentation.util.PreviewFakeData
import com.eva.githubprofileviewer.ui.theme.GitHubProfileViewerTheme
import com.eva.githubprofileviewer.utils.toCompactNumber

@Composable
fun GithubUserSocialDataCard(
    label: String,
    count: Int,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Card(
        modifier = modifier.padding(4.dp),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor, contentColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = count.toCompactNumber(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun GithubUserSocialData(
    model: GitHubUserModel,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    spacing: Arrangement.Horizontal = Arrangement.spacedBy(16.dp)
) {
    Row(
        horizontalArrangement = spacing,
        modifier = modifier
            .padding(2.dp)
    ) {
        GithubUserSocialDataCard(
            label = stringResource(R.string.repositories),
            count = model.repoCount,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = Modifier.weight(1f)
        )
        GithubUserSocialDataCard(
            label = stringResource(R.string.followers),
            count = model.followerCount,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = Modifier.weight(1f)
        )
        GithubUserSocialDataCard(
            label = stringResource(R.string.following),
            count = model.followingCount,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun GithubUserSocialDataPreview() {
    GitHubProfileViewerTheme {
        GithubUserSocialData(
            model = PreviewFakeData.FAKE_USER_MODEL
        )
    }
}
