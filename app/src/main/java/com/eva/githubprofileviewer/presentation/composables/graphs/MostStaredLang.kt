package com.eva.githubprofileviewer.presentation.composables

import androidx.compose.foundation.BorderStroke
import android.graphics.Color as ParseColor
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.StaredLanguageGraphModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostStaredLang(
    languages: List<StaredLanguageGraphModel>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Most Started Language",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Canvas(
                modifier = Modifier
                    .size(200.dp)
                    .padding(20.dp)
            ) {
                var lastAngle = 270f
                val strokeWidth = 30.dp.toPx()

                for (language in languages) {
                    val sweepAngle = language.percentage * 360
                    drawArc(
                        color = Color(android.graphics.Color.parseColor(language.languagesModel.colorCode)),
                        startAngle = lastAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Butt),
                    )
                    lastAngle += sweepAngle
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                userScrollEnabled = false
            ) {
                items(languages.size) { idx ->
                    val lang = languages[idx].languagesModel.copy(
                        percentage = languages[idx].percentage
                    )
                    LanguageTags(languagesModel = lang)
                }
            }
        }
    }

}