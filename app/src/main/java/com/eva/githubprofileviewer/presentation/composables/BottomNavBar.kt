package com.eva.githubprofileviewer.presentation.composables

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eva.githubprofileviewer.presentation.util.InformationTabs

@Composable
fun BottomNavBar(
    currentPage: Int,
    onNavigationItemSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = currentPage == 0,
            onClick = { onNavigationItemSelect(0) },
            label = { Text(text = InformationTabs.UserInformation.label) },
            icon = {
                Icon(
                    imageVector = InformationTabs.UserInformation.icon,
                    contentDescription = InformationTabs.UserInformation.contentDescription
                )
            }

        )
        NavigationBarItem(
            selected = currentPage == 1,
            onClick = { onNavigationItemSelect(1) },
            label = { Text(text = InformationTabs.GraphInformation.label) },
            icon = {
                Icon(
                    imageVector = InformationTabs.GraphInformation.icon,
                    contentDescription = InformationTabs.GraphInformation.contentDescription
                )
            }

        )
        NavigationBarItem(
            selected = currentPage == 2,
            onClick = { onNavigationItemSelect(2) },
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
