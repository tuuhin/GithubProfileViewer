package com.eva.githubprofileviewer.presentation.routes

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eva.githubprofileviewer.presentation.util.LocalSnackBarHostState
import com.eva.githubprofileviewer.utils.Constants
import kotlinx.coroutines.launch

@Composable
fun NavigationGraph(
	modifier: Modifier = Modifier,
	snackBarHostState: SnackbarHostState = LocalSnackBarHostState.current,
) {
	val navController = rememberNavController()
	val scope = rememberCoroutineScope()

	NavHost(
		navController = navController,
		startDestination = Routes.BaseRoute.name,
		modifier = modifier
	) {
		composable(
			route = Routes.BaseRoute.name,
			exitTransition = {
				slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
			}
		) {
			BaseRoute(
				onNavigate = { username ->
					when {
						username.isEmpty() -> scope.launch {
							snackBarHostState.showSnackbar("Cannot have a blank username.Please provide a correct one")
						}

						else -> {
							val userId = username.trim()
							val route = Routes.UserInfoRoute(userId)

							navController.navigate(route.name) {
								popUpTo(Routes.BaseRoute.name)
							}
						}
					}
				}
			)
		}
		composable(
			route = Routes.UserInfoRouteScheme.name,
			arguments = listOf(
				navArgument(Constants.QUERY_PARAMS) {
					type = NavType.StringType
					defaultValue = Constants.DEFAULT_GITHUB_USERNAME
					nullable = false
				}
			),
			enterTransition = {
				slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
			},
			exitTransition =  {
				slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
			}
		) {
			UserInfoRoute()
		}
	}
}
