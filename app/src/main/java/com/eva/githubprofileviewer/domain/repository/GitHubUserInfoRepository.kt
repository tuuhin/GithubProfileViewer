package com.eva.githubprofileviewer.domain.repository

import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GitHubUserInfoRepository {
    suspend fun getUserInfo(user: String): Flow<Resource<GitHubUserModel?>>
    suspend fun getUserRepoInformation(
        user: String,
        count: Int
    ): Flow<Resource<List<GitHubRepositoryModel>?>>
}