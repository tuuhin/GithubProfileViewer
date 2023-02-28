package com.eva.githubprofileviewer.data

import com.apollographql.apollo3.ApolloClient
import com.eva.GitHubRepositoryQuery
import com.eva.GithubGraphInformationQuery
import com.eva.GithubUserQuery
import com.eva.githubprofileviewer.data.mappers.toModel
import com.eva.githubprofileviewer.domain.models.*

class ApolloQueriesImpl(
    private val apolloClient: ApolloClient
) : ApolloQueries {
    override suspend fun getUser(username: String): GitHubUserModel? {
        return apolloClient
            .query(GithubUserQuery(username))
            .execute()
            .data?.user?.toModel()
    }

    override suspend fun getRepositories(
        username: String,
        count: Int
    ): List<GitHubRepositoryModel>? {
        return apolloClient
            .query(GitHubRepositoryQuery(username, count))
            .execute()
            .data?.user?.repositories?.toModel()
    }

    override suspend fun getLanguages(username: String): GithubGraphDataModel? {
        val model = apolloClient
            .query(GithubGraphInformationQuery(username))
            .execute()
            .data?.user?.repositories?.toModel()
        return if (model == null) null else GithubGraphDataModel(model)
    }

}