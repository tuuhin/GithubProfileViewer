package com.eva.githubprofileviewer.data.mappers

import com.eva.GithubUserQuery
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import java.time.LocalDate


fun GithubUserQuery.User.toModel(): GitHubUserModel {

    return GitHubUserModel(
        name = name ?: "",
        avatarUrl = "$avatarUrl",
        followerCount = followers.totalCount,
        followingCount = followers.totalCount,
        createdAt = LocalDate.parse(createdAt.toString()),
        location = location,
        repoCount = repositories.totalCount,
    )
}