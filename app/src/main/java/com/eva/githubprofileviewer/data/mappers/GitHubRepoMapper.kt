package com.eva.githubprofileviewer.data.mappers

import com.eva.GitHubRepositoryQuery
import com.eva.GithubUserQuery
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.domain.models.RepositoryLanguagesModel

fun GitHubRepositoryQuery.Repositories.toModel(): List<GitHubRepositoryModel> {
    return nodes?.map { node ->
        GitHubRepositoryModel(
            name = node?.name ?: "",
            description = node?.description,
            languages = node?.languages?.edges?.map { lang ->
                RepositoryLanguagesModel(
                    name = lang?.node?.name ?: "",
                    colorCode = lang?.node?.color?.toIntOrNull(16) ?: 0xffffff,
                    size = lang?.size ?: 0,
                    percentage = ((lang?.size?.div(node.languages.totalSize)) ?: 1).toFloat()
                )
            }?: emptyList(),
            forksCount = node?.forkCount ?: 0,
            starsCount = node?.stargazerCount ?: 0,
            size = node?.diskUsage ?: 0
        )
    } ?: emptyList()

}