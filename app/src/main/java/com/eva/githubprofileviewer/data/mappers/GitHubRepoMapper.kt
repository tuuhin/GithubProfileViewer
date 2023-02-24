package com.eva.githubprofileviewer.data.mappers

import android.util.Log
import com.eva.GitHubRepositoryQuery
import com.eva.GithubUserQuery
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.domain.models.RepositoryLanguagesModel
import okhttp3.internal.toHexString

fun GitHubRepositoryQuery.Repositories.toModel(): List<GitHubRepositoryModel> {

    return nodes?.map { node ->
        GitHubRepositoryModel(
            name = node?.name ?: "",
            description = node?.description,
            languages = node?.languages?.edges?.map { lang ->



                Log.d("COLOR_CODE",  lang?.node?.color?.toIntOrNull(16).toString())

                RepositoryLanguagesModel(
                    name = lang?.node?.name ?: "",
                    colorCode = lang?.node?.color ?:" 0xffffff",
                    size = lang?.size ?: 0,
                    percentage = (lang?.size?:0).toFloat() / (node.languages.totalSize).toFloat()
                )
            }?: emptyList(),
            forksCount = node?.forkCount ?: 0,
            starsCount = node?.stargazerCount ?: 0,
            size = node?.diskUsage ?: 0,
            issuesCount = node?.issues?.totalCount?:0,

        )
    } ?: emptyList()

}