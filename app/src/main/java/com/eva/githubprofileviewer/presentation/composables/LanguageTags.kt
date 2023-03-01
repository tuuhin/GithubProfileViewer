package com.eva.githubprofileviewer.presentation.composables

import android.graphics.Color as C
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.RepositoryLanguagesModel

@Composable
fun LanguageTags(
    languagesModel: RepositoryLanguagesModel,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.wrapContentSize()
    ) {
        Canvas(
            modifier = Modifier.size(10.dp)
        ) {
            drawCircle(
                color = Color(android.graphics.Color.parseColor(languagesModel.colorCode)),
            )
        }
        Text(
            text = languagesModel.name,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = languagesModel.getPercentageString,
            style = MaterialTheme.typography.labelMedium
        )
    }
}