package com.eva.githubprofileviewer.presentation.routes

sealed class Routes(val name: String) {
    object BaseRoute : Routes("/")
    class UserInfoRoute(val query: String) : Routes("/user?userId=$query")
}


