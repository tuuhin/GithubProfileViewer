package com.eva.githubprofileviewer.presentation.composables.graphs

import androidx.compose.foundation.BorderStroke
import android.graphics.Color as ParserColor
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.LanguageGraphModel
import com.eva.githubprofileviewer.presentation.composables.LanguageTags

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLanguageGraph(
    languages: List<LanguageGraphModel>,
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
                text = "Top Languages",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Canvas(
                modifier = Modifier
                    .size(200.dp)
                    .padding(10.dp)
            ) {
                var lastAngle = 270f
                for (language in languages) {
                    drawArc(
                        color = Color(ParserColor.parseColor(language.languagesModel.colorCode)),
                        startAngle = lastAngle,
                        sweepAngle = language.percentage * 360,
                        useCenter = true,
                    )
                    lastAngle += language.percentage * 360
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