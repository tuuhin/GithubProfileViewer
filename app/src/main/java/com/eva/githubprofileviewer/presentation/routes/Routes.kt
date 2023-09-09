package com.eva.githubprofileviewer.presentation.routes

import com.eva.githubprofileviewer.utils.Constants

sealed class Routes(val name: String) {
	object BaseRoute : Routes(name = "/")

	object UserInfoRouteScheme : Routes(name = "/user?userId={${Constants.QUERY_PARAMS}}")

	data class UserInfoRoute(val query: String) : Routes("/user?userId=$query")
}
