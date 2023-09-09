package com.eva.githubprofileviewer.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.GithubGraphDataModel
import com.eva.githubprofileviewer.presentation.ShowContent
import com.eva.githubprofileviewer.presentation.composables.graphs.MostStaredLang
import com.eva.githubprofileviewer.presentation.composables.graphs.TopLanguageGraph

@Composable
fun UserGraphInformation(
    modifier: Modifier = Modifier,
    content: ShowContent<GithubGraphDataModel>
) {
    when {
        content.isLoading -> Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        else -> content.content?.let { model ->
            LazyColumn(
                modifier = Modifier.padding(4.dp)
            ) {
                item {
                    TopLanguageGraph(
                        languages = model.topLangGraphData,
                        modifier = Modifier
                            .height(400.dp)
                            .padding(0.dp, 2.dp)
                    )
                }
                item {
                    MostStaredLang(
                        languages = model.starsPerLangGraphData,
                        modifier = Modifier
                            .height(400.dp)
                            .padding(0.dp, 2.dp)
                    )
                }
            }
        }
    }
}
