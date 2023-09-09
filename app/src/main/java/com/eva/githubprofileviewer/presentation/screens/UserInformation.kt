package com.eva.githubprofileviewer.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.presentation.ShowContent
import com.eva.githubprofileviewer.presentation.composables.UserDetails

@Composable
fun UserInformation(
    modifier: Modifier = Modifier,
    content: ShowContent<GitHubUserModel>
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            content.isLoading -> CircularProgressIndicator()
            else -> content.content?.let { model -> UserDetails(model = model) }
        }
    }
}
