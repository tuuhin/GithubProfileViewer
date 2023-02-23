package com.eva.githubprofileviewer.domain.models

data class GitHubRepositoryModel(
    val name: String,
    val description: String?,
    val languages: List<RepositoryLanguagesModel?>,
    val starsCount: Int,
    val forksCount: Int,
    val size: Int
)

data class RepositoryLanguagesModel(
    val name: String,
    val colorCode: Int,
    val size: Int,
    val percentage: Float
)