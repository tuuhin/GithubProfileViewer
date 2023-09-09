package com.eva.githubprofileviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.eva.githubprofileviewer.presentation.routes.NavigationGraph
import com.eva.githubprofileviewer.presentation.util.LocalSnackBarHostState
import com.eva.githubprofileviewer.ui.theme.GitHubProfileViewerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
// 		Adds the splash screen
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            val snackBarHostState = remember { SnackbarHostState() }

            GitHubProfileViewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocalProvider(
                        LocalSnackBarHostState provides snackBarHostState
                    ) {
                        NavigationGraph()
                    }
                }
            }
        }
    }
}
