package com.eva.githubprofileviewer.domain.models

import java.time.LocalDate

data class GitHubUserModel(
    val avatarUrl: String,
    val name: String,
    val location: String?,
    val createdAt: LocalDate,
    val followerCount: Int,
    val followingCount: Int,
    val repoCount: Int,
)
