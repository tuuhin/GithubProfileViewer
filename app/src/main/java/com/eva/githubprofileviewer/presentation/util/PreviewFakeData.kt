package com.eva.githubprofileviewer.presentation.util

import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.domain.models.RepositoryLanguagesModel
import java.time.LocalDate

object PreviewFakeData {

    val FAKE_USER_MODEL = GitHubUserModel(
        name = "Previewer",
        url = "<someurl>",
        bio = "I help in previewing the data in the compose previews",
        createdAt = LocalDate.of(2023, 1, 1),
        avatarUrl = "",
        email = "iama_preview@android.com",
        location = "Kotlin Island",
        twitterUserName = "@previewer",
        login = "previewer",
        websiteUrl = "<some url>",
        followerCount = 0,
        followingCount = 0,
        repoCount = 10
    )

    val FAKE_REPOSITORY_LANGUAGE_MODEL =
        RepositoryLanguagesModel(name = "Kotlin", colorCode = "#800080")

    val FAKE_REPOSITORY_MODEL = GitHubRepositoryModel(
        name = "Android",
        description = "The mobile in your hand",
        languages = List(2) { FAKE_REPOSITORY_LANGUAGE_MODEL },
        starsCount = 4020,
        forksCount = 1345,
        issuesCount = 5000,
        size = 234567,
        url = null
    )
}
