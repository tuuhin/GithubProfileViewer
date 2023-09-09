package com.eva.githubprofileviewer.presentation.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.presentation.ShowContent
import com.eva.githubprofileviewer.presentation.composables.RepositoryCard
import com.eva.githubprofileviewer.presentation.composables.UserRepositoriesTopBar
import com.eva.githubprofileviewer.utils.RepositoryArrangement

@Composable
fun UserRepositoryInformation(
	modifier: Modifier = Modifier,
	context: Context = LocalContext.current,
	content: ShowContent<List<GitHubRepositoryModel>>,
	onReArrange: (RepositoryArrangement) -> Unit
) {
	Scaffold(
		topBar = { UserRepositoriesTopBar(onReArrange = onReArrange) }
	) { scPadding ->

		when {
			content.isLoading -> Box(
				modifier = modifier.fillMaxSize(),
				contentAlignment = Alignment.Center
			) {
				CircularProgressIndicator()
			}

			else -> content.content?.let { repos ->
				LazyColumn(
					contentPadding = scPadding
				) {
					items(repos.size) { idx ->
						val model = repos[idx]
						RepositoryCard(
							repositoryModel = model,
							onClick = {
								model.url?.let { url ->
									val uri = Uri.parse(url)
									val intent = Intent(Intent.ACTION_VIEW, uri)
									context.startActivity(intent)
								}
							}
						)
					}
				}
			}
		}
	}
}
