package com.eva.githubprofileviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eva.githubprofileviewer.presentation.routes.BaseRoute
import com.eva.githubprofileviewer.presentation.routes.Routes
import com.eva.githubprofileviewer.presentation.routes.UserInfoRoute
import com.eva.githubprofileviewer.ui.theme.GitHubProfileViewerTheme
import com.eva.githubprofileviewer.utils.Constants


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            GitHubProfileViewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "/"
                    ) {
                        composable(Routes.BaseRoute.name) {
                            BaseRoute(navController)
                        }
                        composable(
                            Routes.UserInfoRoute("{${Constants.QUERY_PARAMS}}").name,
                            arguments = listOf(navArgument(Constants.QUERY_PARAMS) {
                                type = NavType.StringType
                                defaultValue = Constants.DEFAULT_GITHUB_USERNAME
                            })
                        ) { entry ->
                            UserInfoRoute(entry.arguments?.getString(Constants.QUERY_PARAMS) ?: "")
                        }
                    }
                }
            }
        }
    }
}

