package com.eva.githubprofileviewer.presentation.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavBar(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = pagerState.currentPage == 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            },
            label = { Text(text = InformationTabs.UserInformation.label) },
            icon = {
                Icon(
                    imageVector = InformationTabs.UserInformation.icon,
                    contentDescription = InformationTabs.UserInformation.contentDescription
                )
            }

        )
        NavigationBarItem(
            selected = pagerState.currentPage == 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(1)
                }
            },
            label = { Text(text = InformationTabs.GraphInformation.label) },
            icon = {
                Icon(
                    imageVector = InformationTabs.GraphInformation.icon,
                    contentDescription = InformationTabs.GraphInformation.contentDescription
                )
            }

        )
        NavigationBarItem(
            selected = pagerState.currentPage == 2,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(2)
                }
            },
            label = { Text(text = InformationTabs.RepositoryInformation.label) },
            icon = {
                Icon(
                    imageVector = InformationTabs.RepositoryInformation.icon,
                    contentDescription = InformationTabs.RepositoryInformation.contentDescription
                )
            }

        )
    }
}