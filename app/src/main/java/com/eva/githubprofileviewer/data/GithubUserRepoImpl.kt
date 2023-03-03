package com.eva.githubprofileviewer.data

import com.apollographql.apollo3.exception.ApolloException
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.domain.models.GithubGraphDataModel
import com.eva.githubprofileviewer.domain.repository.GitHubUserInfoRepository
import com.eva.githubprofileviewer.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubUserRepoImpl(
    private val queries: ApolloQueries
) : GitHubUserInfoRepository {
    override suspend fun getUserInformation(user: String)
            : Flow<Resource<GitHubUserModel?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val model = queries.getUser(user)
                emit(Resource.Success(data = model))
            } catch (e: ApolloException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message ?: "Apollo exception occurred"))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message ?: "Unknown Error"))
            }
        }

    }

    override suspend fun getRepoInformation(
        user: String,
        count: Int
    ): Flow<Resource<List<GitHubRepositoryModel>?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val model = queries.getRepositories(user, count)
                emit(Resource.Success(data = model))
            } catch (e: ApolloException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message ?: "Apollo exception occurred"))
            }  catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message ?: "Unknown Error"))
            }
        }
    }

    override suspend fun getGraphData(user: String)
            : Flow<Resource<GithubGraphDataModel?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val model = queries.getLanguages(user)
                emit(Resource.Success(data = model))
            } catch (e: ApolloException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message ?: "Apollo Error Occurred"))
            }  catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message ?: "Unknown error"))
            }
        }
    }

}
