package com.eva.githubprofileviewer.data.mappers

import com.eva.GitHubRepositoryQuery
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.models.RepositoryLanguagesModel
import com.eva.githubprofileviewer.utils.Constants

fun GitHubRepositoryQuery.Repositories.toModel(): List<GitHubRepositoryModel> {

    return nodes?.map { node ->

        GitHubRepositoryModel(
            name = node?.name ?: "",
            description = node?.description,
            languages = node?.languages?.edges?.map { lang ->
                RepositoryLanguagesModel(
                    name = lang?.node?.name ?: "",
                    colorCode = lang?.node?.color ?: Constants.NOT_FOUND_COLOR,
                    size = lang?.size?.toFloat() ?: 0f,
                    percentage = (lang?.size ?: 0).toFloat() / (node.languages.totalSize).toFloat()
                )
            } ?: emptyList(),
            forksCount = node?.forkCount ?: 0,
            starsCount = node?.stargazerCount ?: 0,
            size = node?.diskUsage ?: 0,
            issuesCount = node?.issues?.totalCount ?: 0,
            url = node?.url?.toString()
        )
    } ?: emptyList()

}