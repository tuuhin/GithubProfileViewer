package com.eva.githubprofileviewer.data.mappers

import com.eva.GithubGraphInformationQuery
import com.eva.githubprofileviewer.domain.models.GithubLanguageModel
import com.eva.githubprofileviewer.domain.models.RepositoryLanguagesModel
import com.eva.githubprofileviewer.utils.Constants

fun GithubGraphInformationQuery.Repositories.toModel(): List<GithubLanguageModel> {
    return edges?.map { node ->
        GithubLanguageModel(
            languagesModel = RepositoryLanguagesModel(
                name = node?.node?.primaryLanguage?.name ?: "",
                colorCode = node?.node?.primaryLanguage?.color ?: Constants.NOT_FOUND_COLOR,
            ),
            starsCount = node?.node?.stargazerCount ?: 0,
            repositoryName = node?.node?.name ?:""
        )
    } ?: emptyList()

}