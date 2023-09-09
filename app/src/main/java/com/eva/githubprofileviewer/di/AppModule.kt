package com.eva.githubprofileviewer.di

import com.eva.githubprofileviewer.data.ApolloQueries
import com.eva.githubprofileviewer.data.ApolloQueriesImpl
import com.eva.githubprofileviewer.data.GithubUserRepoImpl
import com.eva.githubprofileviewer.data.RemoteClient
import com.eva.githubprofileviewer.domain.repository.GitHubUserInfoRepository
import com.eva.githubprofileviewer.presentation.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

	singleOf(RemoteClient::getApolloClientInstance)

	factoryOf(::ApolloQueriesImpl) bind ApolloQueries::class

	factoryOf(::GithubUserRepoImpl) bind GitHubUserInfoRepository::class

	viewModelOf(::UserInfoViewModel)
}
