package com.eva.githubprofileviewer.presentation.composables

import android.graphics.Color as ParserColor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryCard(
    repositoryModel: GitHubRepositoryModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(10.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Text(
                text = repositoryModel.name,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                letterSpacing = 0.75.sp
            )

            repositoryModel.description?.let {
                Text(
                    text = repositoryModel.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
            ) {
                AssociatedIcons(
                    icon = Icons.Default.Person,
                    count = repositoryModel.forksCount
                )
                AssociatedIcons(
                    icon = Icons.Default.Star,
                    count = repositoryModel.starsCount,
                )
                AssociatedIcons(
                    icon = Icons.Default.AccountBox,
                    count = repositoryModel.issuesCount
                )
            }
            if (repositoryModel.languages.isNotEmpty()) {

                Text(
                    text = "Languages Composition",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(4.dp, 8.dp)
                )
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    val totalWidth = size.width
                    var lastOffset = Offset.Zero

                    drawRect(color = Color.Gray)


                    for (language in repositoryModel.languages) {

                        drawRect(
                            color = Color(ParserColor.parseColor(language.colorCode)),
                            topLeft = lastOffset,
                            size = Size(language.percentage * totalWidth, size.height),

                            )
                        lastOffset = Offset(language.percentage * totalWidth, 0f)
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


@Composable
fun AssociatedIcons(
    icon: ImageVector,
    count: Int,
    modifier: Modifier = Modifier,
    contentDescription: String = "Icons description",
) {
    Column(
        modifier = modifier
            .wrapContentSize(Alignment.CenterStart),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = count.toString())
    }
}