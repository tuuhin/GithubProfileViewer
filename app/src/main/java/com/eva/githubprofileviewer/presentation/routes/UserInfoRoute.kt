package com.eva.githubprofileviewer.presentation.routes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.eva.githubprofileviewer.presentation.UiEvent
import com.eva.githubprofileviewer.presentation.UserInfoViewModel
import com.eva.githubprofileviewer.presentation.composables.BottomNavBar
import com.eva.githubprofileviewer.presentation.screens.UserGraphInformation
import com.eva.githubprofileviewer.presentation.screens.UserInformation
import com.eva.githubprofileviewer.presentation.screens.UserRepositoryInformation
import com.eva.githubprofileviewer.presentation.util.LocalSnackBarHostState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserInfoRoute(
	modifier: Modifier = Modifier,
	userInfoViewModel: UserInfoViewModel = koinViewModel(),
	snackBarHostState: SnackbarHostState = LocalSnackBarHostState.current
) {
	val scope = rememberCoroutineScope()

	val pager = rememberPagerState(
		initialPage = 0,
		initialPageOffsetFraction = 0f,
		pageCount = { 3 }
	)

	LaunchedEffect(Unit) {
		userInfoViewModel.eventFlow.collect { event ->
			when (event) {
				is UiEvent.ShowSnackBar -> {
					snackBarHostState.showSnackbar(event.text)
				}
			}
		}
	}
	Scaffold(
		snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
		bottomBar = {
			BottomNavBar(
				currentPage = pager.currentPage,
				onNavigationItemSelect = { idx ->
					scope.launch { pager.animateScrollToPage(idx) }
				}
			)
		}
	) { padding ->
		HorizontalPager(
			state = pager,
			modifier = modifier.padding(padding)
		) { idx ->
			when (idx) {
				0 -> UserInformation(
					content = userInfoViewModel.userInfoState.value
				)

				1 -> UserGraphInformation(
					content = userInfoViewModel.graphState.value
				)

				2 -> UserRepositoryInformation(
					content = userInfoViewModel.repositoryState.value,
					onReArrange = userInfoViewModel::rearrangeRepository
				)
			}
		}
	}
}
