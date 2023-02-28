package com.eva.githubprofileviewer.domain.repository

import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.domain.models.GithubGraphDataModel
import com.eva.githubprofileviewer.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GitHubUserInfoRepository {
    suspend fun getUserInformation(user: String): Flow<Resource<GitHubUserModel?>>
    suspend fun getRepoInformation(
        user: String,
        count: Int
    ): Flow<Resource<List<GitHubRepositoryModel>?>>

    suspend fun getGraphData(user: String): Flow<Resource<GithubGraphDataModel?>>
}