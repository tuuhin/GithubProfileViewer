package com.eva.githubprofileviewer.presentation.routes


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.eva.githubprofileviewer.presentation.UiEvent
import com.eva.githubprofileviewer.presentation.UserInfoViewModel
import com.eva.githubprofileviewer.presentation.UserInformation
import com.eva.githubprofileviewer.presentation.screens.BottomNavBar
import com.eva.githubprofileviewer.presentation.screens.UserGraphInformation
import com.eva.githubprofileviewer.presentation.screens.UserRepositoryInformation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun UserInfoRoute(
    userId: String,
    modifier: Modifier = Modifier,
    userInfoViewModel: UserInfoViewModel = koinViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val pager = rememberPagerState()

    LaunchedEffect(true) {
        userInfoViewModel.eventFlow.collectLatest { event ->
            Log.d("EVENT_FLOW", event.text)
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(event.text)
                }
            }
        }
    }
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = userId,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            BottomNavBar(pagerState = pager)
        }
    ) { padding ->
        HorizontalPager(
            state = pager,
            count = 3,
            modifier = modifier.padding(padding)
        ) { idx ->
            when (idx) {
                0 -> UserInformation()
                1 -> UserGraphInformation()
                2 -> UserRepositoryInformation()
            }
        }
    }
}