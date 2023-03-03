package com.eva.githubprofileviewer.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import com.eva.githubprofileviewer.data.ApolloQueries
import com.eva.githubprofileviewer.data.ApolloQueriesImpl
import com.eva.githubprofileviewer.data.GithubUserRepoImpl
import com.eva.githubprofileviewer.data.interceptor.AuthorizationInterceptor
import com.eva.githubprofileviewer.domain.repository.GitHubUserInfoRepository
import com.eva.githubprofileviewer.presentation.UserInfoViewModel
import com.eva.githubprofileviewer.utils.Constants
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }
    single {
        ApolloClient.Builder()
            .serverUrl(Constants.GITHUB_URL)
            .okHttpClient(get())
            .normalizedCache(SqlNormalizedCacheFactory(Constants.APOLLODATABASE_NAME))
            .build()
    }

    single<ApolloQueries> {
        ApolloQueriesImpl(get())
    }

    single<GitHubUserInfoRepository> {
        GithubUserRepoImpl(get())
    }
    viewModel {
        UserInfoViewModel(get(), get())
    }
}