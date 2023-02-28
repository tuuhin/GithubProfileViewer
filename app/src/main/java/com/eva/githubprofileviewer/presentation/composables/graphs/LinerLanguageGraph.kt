package com.eva.githubprofileviewer.presentation.composables.graphs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.RepositoryLanguagesModel

@Composable
fun LinearLanguageGraph(
    languagesModels: List<RepositoryLanguagesModel>,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(15.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {
        val totalWidth = size.width
        var lastOffset = Offset.Zero
        // The gray color will fill the background
        drawRect(color = Color.Gray)
        for (language in languagesModels) {
            drawRect(
                color = Color(android.graphics.Color.parseColor(language.colorCode)),
                topLeft = lastOffset,
                size = Size(language.percentage * totalWidth, size.height),

                )
            lastOffset = Offset(lastOffset.x + language.percentage * totalWidth, 0f)
        }
    }
}