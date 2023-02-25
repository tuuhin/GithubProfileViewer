package com.eva.githubprofileviewer.presentation.composables

import android.graphics.Color as ParserColor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.ForkRight
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryCard(
    repositoryModel: GitHubRepositoryModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.padding(10.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
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
                Divider(modifier = Modifier.padding(4.dp))
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    val totalWidth = size.width
                    var lastOffset = Offset.Zero
                    // The gray color will fill the background
                    drawRect(color = Color.Gray)
                    for (language in repositoryModel.languages) {
                        drawRect(
                            color = Color(ParserColor.parseColor(language.colorCode)),
                            topLeft = lastOffset,
                            size = Size(language.percentage * totalWidth, size.height),

                            )
                        lastOffset = Offset(lastOffset.x + language.percentage * totalWidth, 0f)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                for (language in repositoryModel.languages) {
                    LanguageTags(language)

                }
            }
        }
    }
}


