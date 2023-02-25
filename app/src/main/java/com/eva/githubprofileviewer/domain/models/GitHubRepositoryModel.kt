package com.eva.githubprofileviewer.domain.models

import kotlin.math.roundToInt

data class GitHubRepositoryModel(
    val name: String,
    val description: String?,
    val languages: List<RepositoryLanguagesModel>,
    val starsCount: Int,
    val forksCount: Int,
    val issuesCount: Int,
    val size: Int,
) {
    val sizeReadable: String = if (size < 1024) "$size KB" else "${size / 1024} MB"
}

data class RepositoryLanguagesModel(
    val name: String,
    val colorCode: String,
    val size: Int,
    val percentage: Float
) {
    val getPercentageString: String = "${(((percentage * 1000).roundToInt().toFloat() / 10))} %"

}