package com.eva.githubprofileviewer.domain.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class GitHubUserModel(
    val name: String,
    val url: String,
    val bio: String?,
    val createdAt: LocalDate,
    val avatarUrl: String,
    val email: String?,
    val location: String?,
    val twitterUserName: String?,
    val login: String,
    val websiteUrl: String?,
    val followerCount: Int,
    val followingCount: Int,
    val repoCount: Int
) {

    val styledLogin = "@$login"

    val twitterName = "@$twitterUserName"
    fun readableCreatedAt(): String {
        val date = when (
            val currentDate = createdAt.format(DateTimeFormatter.ofPattern("d"))
        ) {
            "1" -> "1st"
            "2" -> "2nd"
            "3" -> "3rd"
            else -> "${currentDate}th"
        }
        return "$date ${createdAt.format(DateTimeFormatter.ofPattern("MMMM,yyyy"))}"
    }
}
