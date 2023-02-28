package com.eva.githubprofileviewer.presentation.composables

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.GithubLanguageModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostStaredRepositories(
    repositories: List<GithubLanguageModel>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier

                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            val width = size.width
            val height = size.height
            val diff = 8.dp
            var gridXOffset = 0f
            var gridYOffset = 0f
            // Drawing the grid x axis
            for (x in 0..width.toInt() step (diff.toPx().toInt())) {
                drawLine(
                    Color.Gray,
                    Offset(gridXOffset, 0f),
                    Offset(gridXOffset, size.height),
                    1.dp.toPx(),
                    StrokeCap.Round
                )
                gridXOffset += diff.toPx()
            }
            // Drawing the grid y axis
            for (y in 0..height.toInt() step (diff.toPx().toInt())) {
                drawLine(
                    Color.Gray,
                    Offset(0f, gridYOffset),
                    Offset(size.width, gridYOffset),
                    1.dp.toPx(),
                    StrokeCap.Round
                )
                gridYOffset += diff.toPx()
            }

            val thickness = width / repositories.size.toFloat()
            var difference = width / repositories.size.toFloat()
            // draw chat
            for (repos in repositories) {
                val barHeight = (repos.starsCount.toFloat() / repositories.maxOf { it.starsCount }
                    .toFloat()) * height
                Log.d("REPO_RUNNER", "$barHeight")
                drawRect(
                    color = Color(android.graphics.Color.parseColor(repos.languagesModel.colorCode)),
                    topLeft = Offset(difference, height - barHeight),
                    size = Size(thickness, barHeight)
                )
                difference += difference
            }

        }
        for (repo in repositories) {
            Column(

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(
                        modifier = Modifier.size(20.dp, 10.dp)
                    ) {
                        drawRect(
                            color = Color(android.graphics.Color.parseColor(repo.languagesModel.colorCode))
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = repo.repositoryName)

                }
            }
        }

    }
}