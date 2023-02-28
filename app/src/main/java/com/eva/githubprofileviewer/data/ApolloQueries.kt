package com.eva.githubprofileviewer.data

import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.domain.models.GithubGraphDataModel

interface ApolloQueries {
    suspend fun getUser(username: String): GitHubUserModel?
    suspend fun getRepositories(username: String,count:Int): List<GitHubRepositoryModel>?
    suspend fun getLanguages(username: String): GithubGraphDataModel?
}