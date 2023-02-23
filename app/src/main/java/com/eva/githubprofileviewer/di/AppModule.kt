package com.eva.githubprofileviewer.di

import com.apollographql.apollo3.ApolloClient
import com.eva.githubprofileviewer.data.GithubUserRepoImpl
import com.eva.githubprofileviewer.domain.repository.GitHubUserInfoRepository
import com.eva.githubprofileviewer.utils.Constants
import org.koin.dsl.module

val appModule = module {
    single<ApolloClient> {
        ApolloClient.Builder()
            .serverUrl(Constants.GITHUB_URL)
            .build()
    }
    single<GitHubUserInfoRepository> {
        GithubUserRepoImpl(get())
    }
}