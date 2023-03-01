package com.eva.githubprofileviewer.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eva.githubprofileviewer.presentation.composables.UserDetails
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserInformation(
    modifier: Modifier = Modifier,
    userInfoViewModel: UserInfoViewModel = koinViewModel()
) {
    val state = userInfoViewModel.userInfoState.value
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            state.content?.let { model ->
                UserDetails(model = model)
            }
        }
    }

}