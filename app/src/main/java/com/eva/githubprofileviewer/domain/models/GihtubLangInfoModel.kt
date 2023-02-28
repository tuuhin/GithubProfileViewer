package com.eva.githubprofileviewer.domain.models

data class GithubLanguageModel(
    val languagesModel: RepositoryLanguagesModel,
    val starsCount: Int,
    val repositoryName: String,
)

data class LanguageGraphModel(
    val languagesModel: RepositoryLanguagesModel,
    val repoCountWithLanguage: Int,
    val percentage: Float,
)

data class StaredLanguageGraphModel(
    val languagesModel: RepositoryLanguagesModel,
    val totalStarCount: Int,
    val percentage: Float,
)

