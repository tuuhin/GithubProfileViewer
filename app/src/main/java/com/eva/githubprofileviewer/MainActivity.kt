package com.eva.githubprofileviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eva.githubprofileviewer.di.appModule
import com.eva.githubprofileviewer.presentation.routes.BaseRoute
import com.eva.githubprofileviewer.presentation.routes.Routes
import com.eva.githubprofileviewer.presentation.routes.UserInfoRoute
import com.eva.githubprofileviewer.ui.theme.GitHubProfileViewerTheme
import com.eva.githubprofileviewer.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }

    override fun onStop() {
        super.onStop()
        stopKoin()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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

