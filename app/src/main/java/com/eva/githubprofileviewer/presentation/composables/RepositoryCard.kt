package com.eva.githubprofileviewer.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.presentation.composables.graphs.LinearLanguageGraph
import com.eva.githubprofileviewer.presentation.util.PreviewFakeData
import com.eva.githubprofileviewer.ui.theme.GitHubProfileViewerTheme

@Composable
fun RepositoryCard(
    repositoryModel: GitHubRepositoryModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .padding(10.dp)
            .clickable(onClick = onClick),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier.padding(2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    Icons.Outlined.Book,
                    contentDescription = "Repository Icon",
                    tint = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = repositoryModel.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Divider(
                modifier = Modifier.padding(0.dp, 2.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            repositoryModel.description?.let {
                Text(
                    text = repositoryModel.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Text(
                text = buildAnnotatedString {
                    append("Repository Size : ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                        append(repositoryModel.sizeReadable)
                    }
                },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(2.dp)
            )
            RepositoryAssociatedIconsInfo(repositoryModel = repositoryModel)
            if (repositoryModel.languages.isNotEmpty()) {
                Text(
                    text = "Languages Composition",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(4.dp)
                )
                LinearLanguageGraph(repositoryModel.languages)
                repositoryModel.languages.forEach { language ->
                    LanguageTags(language)
                }
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun RepositoryCardPreview() {
    GitHubProfileViewerTheme {
        RepositoryCard(
            repositoryModel = PreviewFakeData.FAKE_REPOSITORY_MODEL,
            onClick = { }
        )
    }
}
