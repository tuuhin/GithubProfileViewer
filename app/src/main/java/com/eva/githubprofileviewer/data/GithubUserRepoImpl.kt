package com.eva.githubprofileviewer.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.eva.GitHubRepositoryQuery
import com.eva.GithubUserQuery
import com.eva.githubprofileviewer.data.mappers.toModel
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.domain.repository.GitHubUserInfoRepository
import com.eva.githubprofileviewer.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.HttpRetryException

class GithubUserRepoImpl(
    private val client: ApolloClient
) : GitHubUserInfoRepository {
    override suspend fun getUserInfo(user: String): Flow<Resource<GitHubUserModel?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val model = client.query(GithubUserQuery(user))
                    .execute().data?.user?.toModel()
                emit(Resource.Success(data = model))
            } catch (e: ApolloException) {
                emit(Resource.Error(message = "Apollo exception occured"))
            } catch (e: HttpRetryException) {
                emit(Resource.Error(message = "Http retry exception"))
            } catch (e: Exception) {
                emit(Resource.Error(message = "Unknown Error"))
            }
        }

    }

    override suspend fun getUserRepoInformation(
        user: String,
        count: Int
    ): Flow<Resource<List<GitHubRepositoryModel>?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val model = client
                    .query(GitHubRepositoryQuery(user, count))
                    .execute()
                    .data?.user?.repositories?.toModel()
                emit(Resource.Success(data = model))
            } catch (e: ApolloException) {
                emit(Resource.Error(message = "Apollo exception occured"))
            } catch (e: HttpRetryException) {
                emit(Resource.Error(message = "Http retry exception"))
            } catch (e: Exception) {
                emit(Resource.Error(message = "Unknown Error"))
            }
        }
    }

}
