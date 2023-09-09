package com.eva.githubprofileviewer.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import com.eva.githubprofileviewer.data.interceptor.AuthorizationInterceptor
import com.eva.githubprofileviewer.utils.Constants
import okhttp3.OkHttpClient
import java.time.Duration

object RemoteClient {

	private val okHttpClientInstance = OkHttpClient.Builder()
		.addInterceptor(AuthorizationInterceptor())
		.callTimeout(Duration.ofSeconds(10))
		.connectTimeout(Duration.ofSeconds(10))
		.build()

	fun getApolloClientInstance() = ApolloClient.Builder()
		.serverUrl(Constants.GITHUB_URL)
		.okHttpClient(okHttpClientInstance)
		.normalizedCache(SqlNormalizedCacheFactory(Constants.APOLLO_DATABASE_NAME))
		.fetchPolicy(FetchPolicy.NetworkFirst)
		.build()
}