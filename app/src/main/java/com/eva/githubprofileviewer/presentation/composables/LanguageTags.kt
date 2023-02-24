package com.eva.githubprofileviewer.presentation.composables

import android.graphics.Color as ParserColor
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
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
        modifier = modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier.size(10.dp)
        ) {
            drawCircle(color = Color(android.graphics.Color.parseColor(languagesModel.colorCode)))
        }

        Spacer(modifier = Modifier.width(4.dp))
        Text(text = languagesModel.name)
        Spacer(modifier = modifier.width(4.dp))
        Text(text = languagesModel.getPercentageString)
    }
}