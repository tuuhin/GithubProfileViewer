package com.eva.githubprofileviewer.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class InformationTabs(
    val label: String,
    val icon: ImageVector,
    val contentDescription: String? = null
) {
    object UserInformation :
        InformationTabs(
            "User",
            icon = Icons.Default.Person,
            contentDescription = "User Tab"
        )

    object GraphInformation :
        InformationTabs(
            "Graph",
            icon = Icons.Default.ShowChart,
            contentDescription = "Graph Tab"
        )

    object RepositoryInformation :
        InformationTabs(
            "Repository",
            icon = Icons.Default.Bookmarks,
            contentDescription = "Repository Tab"
        )
}
