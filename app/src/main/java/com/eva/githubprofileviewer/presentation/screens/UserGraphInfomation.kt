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
import com.eva.githubprofileviewer.presentation.UserInfoViewModel
import com.eva.githubprofileviewer.presentation.composables.MostStaredLang
import com.eva.githubprofileviewer.presentation.composables.TopLanguageGraph
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserGraphInformation(
    modifier: Modifier = Modifier,
    userInfoViewModel: UserInfoViewModel = koinViewModel()
) {
    val state = userInfoViewModel.graphState.value

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        state.content?.let {
            LazyColumn(
                modifier = Modifier.padding(4.dp)
            ) {
                item {
                    TopLanguageGraph(
                        languages = state.content.topLangGraphData,
                        modifier = Modifier
                            .height(400.dp)
                            .padding(0.dp, 2.dp)
                    )
                }
                item {
                    MostStaredLang(
                        languages = state.content.starsPerLangGraphData,
                        modifier = Modifier
                            .height(400.dp)
                            .padding(0.dp, 2.dp)
                    )
                }
            }
        }

    }
}

