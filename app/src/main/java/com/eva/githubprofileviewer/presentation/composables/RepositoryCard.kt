package com.eva.githubprofileviewer.presentation.composables


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.ForkRight
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.presentation.composables.graphs.LinearLanguageGraph
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryCard(
    repositoryModel: GitHubRepositoryModel,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {

    Card(
        modifier = modifier
            .padding(10.dp)
            .clickable {
                val uri = Uri.parse(repositoryModel.url)
                val intent = Intent(Intent.ACTION_VIEW,uri)
                context.startActivity(intent)
            },
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier.padding(2.dp)
            ) {
                Icon(Icons.Outlined.Book, contentDescription = "Repository Icon")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = repositoryModel.name,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    letterSpacing = 0.75.sp,
                    color = MaterialTheme.colorScheme.primary
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
                )
            }
            Text(
                text = "${"Repository Size:".uppercase(Locale.ROOT)} : ${repositoryModel.sizeReadable}",
                modifier = Modifier.padding(2.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
            ) {
                AssociatedIcons(
                    icon = Icons.Outlined.ForkRight,
                    label = "Forks",
                    count = repositoryModel.forksCount
                )
                AssociatedIcons(
                    icon = Icons.Outlined.Star,
                    label = "Stars",
                    count = repositoryModel.starsCount,
                )
                AssociatedIcons(
                    icon = Icons.Outlined.Message,
                    label = "Issues",
                    count = repositoryModel.issuesCount
                )
            }
            if (repositoryModel.languages.isNotEmpty()) {
                Text(
                    text = "Languages Composition",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(4.dp, 0.dp)
                )
                LinearLanguageGraph(repositoryModel.languages)
                for (language in repositoryModel.languages) {
                    LanguageTags(language)

                }
            }
        }
    }
}


