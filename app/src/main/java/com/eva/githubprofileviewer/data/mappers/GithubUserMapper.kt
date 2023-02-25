package com.eva.githubprofileviewer.data.mappers

import com.eva.GithubUserQuery
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun GithubUserQuery.User.toModel(): GitHubUserModel {

    return GitHubUserModel(
        name = name ?: "",
        email = email.ifEmpty { null },
        avatarUrl = "$avatarUrl",
        followerCount = followers.totalCount,
        followingCount = following.totalCount,
        createdAt = LocalDateTime.parse(
            createdAt.toString(),
            DateTimeFormatter.ISO_OFFSET_DATE_TIME
        )
            .toLocalDate(),
        location = location,
        repoCount = repositories.totalCount,
        bio = bio,
        login =login,
        url = url.toString(),
        twitterUserName = twitterUsername,
        websiteUrl = websiteUrl.toString()


    )
}