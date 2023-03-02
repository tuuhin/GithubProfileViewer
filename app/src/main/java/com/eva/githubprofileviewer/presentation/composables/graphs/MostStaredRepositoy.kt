package com.eva.githubprofileviewer.presentation.composables.graphs

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    // This file is not implemented due the absence of a proper Ui
    // It maybe implemented in the future
    Card(
        modifier = modifier.padding(10.dp)
    ) {
        Text(text = "Most Stared Repository")
        Canvas(
            modifier = Modifier
                .size(400.dp)
                .padding(10.dp)
        ) {
            val width = size.width
            val height = size.height
            drawLine(
                Color.Gray,
                Offset.Zero,
                Offset(0f, height),
                2.dp.toPx(),
                cap = StrokeCap.Butt,
            )
            drawLine(
                Color.Gray,
                Offset(0f, size.height),
                Offset(size.width, size.height),
                2.dp.toPx(),
                cap = StrokeCap.Butt
            )
            val thickness = (width - 20f) / repositories.size
            var difference = 10f
            for (repos in repositories) {
                val barHeight = (repos.starsCount.toFloat() / repositories.maxOf { it.starsCount }
                    .toFloat()) * height

                Log.d("REPO_RUNNER", "$barHeight")
                drawRect(
                    color = Color(android.graphics.Color.parseColor(repos.languagesModel.colorCode)),
                    topLeft = Offset(difference, height - barHeight),
                    size = Size(thickness, barHeight)
                )
                difference += thickness
            }

        }
//        for (repo in repositories) {
//            Column {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Canvas(
//                        modifier = Modifier.size(20.dp, 10.dp)
//                    ) {
//                        drawRect(
//                            color = Color(android.graphics.Color.parseColor(repo.languagesModel.colorCode))
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(text = repo.repositoryName)
//                }
//            }
//        }

    }
}


