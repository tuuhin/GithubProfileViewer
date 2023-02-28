package com.eva.githubprofileviewer.domain.models

data class GitHubRepositoryModel(
    val name: String,
    val description: String?,
    val languages: List<RepositoryLanguagesModel>,
    val starsCount: Int,
    val forksCount: Int,
    val issuesCount: Int,
    val size: Int,
    val url:String?
) {
    val sizeReadable: String = if (size < 1024) "$size KB" else "${size / 1024} MB"
}

