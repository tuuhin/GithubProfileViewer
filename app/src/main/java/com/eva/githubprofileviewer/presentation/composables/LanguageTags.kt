package com.eva.githubprofileviewer.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.RepositoryLanguagesModel
import com.eva.githubprofileviewer.presentation.util.PreviewFakeData
import com.eva.githubprofileviewer.ui.theme.GitHubProfileViewerTheme
import android.graphics.Color as C

@Composable
fun LanguageTags(
    languagesModel: RepositoryLanguagesModel,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    percentageColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentSize()
            .padding(2.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Canvas(
            modifier = Modifier.size(10.dp)
        ) {
            drawCircle(
                color = Color(C.parseColor(languagesModel.colorCode))
            )
        }
        Text(
            text = languagesModel.name,
            style = MaterialTheme.typography.labelLarge,
            color = textColor
        )
        Text(
            text = languagesModel.getPercentageString,
            color = percentageColor,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview
@Composable
fun LanguageTagsPreview() {
    GitHubProfileViewerTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            LanguageTags(languagesModel = PreviewFakeData.FAKE_REPOSITORY_LANGUAGE_MODEL)
        }
    }
}
