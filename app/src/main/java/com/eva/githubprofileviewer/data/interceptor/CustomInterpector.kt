package com.eva.githubprofileviewer.data.interceptor

import com.eva.githubprofileviewer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader("Authorization","Bearer ${BuildConfig.TOKEN}")
            .build()

        return chain.proceed(request)
    }

}