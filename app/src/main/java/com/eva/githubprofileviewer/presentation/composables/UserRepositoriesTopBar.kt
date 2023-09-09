package com.eva.githubprofileviewer.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.eva.githubprofileviewer.R
import com.eva.githubprofileviewer.utils.RepositoryArrangement

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRepositoriesTopBar(
	onReArrange: (RepositoryArrangement) -> Unit,
	modifier: Modifier = Modifier,
) {
	var menuState by remember { mutableStateOf(false) }
	var selectedItem by remember {
		mutableStateOf<RepositoryArrangement>(RepositoryArrangement.BySize)
	}

	val dropDownItems = remember {
		listOf(
			RepositoryArrangement.BySize,
			RepositoryArrangement.ByFork,
			RepositoryArrangement.ByStars
		)
	}

	TopAppBar(
		title = {
			Text(
				text = stringResource(id = R.string.top_repo),
				style = MaterialTheme.typography.titleLarge
			)
		},
		actions = {
			Box(
				modifier = Modifier.wrapContentWidth()
			) {
				TextButton(
					onClick = { menuState = !menuState },
					shape = MaterialTheme.shapes.medium,
					modifier = Modifier.padding(16.dp, 8.dp),
					colors = ButtonDefaults.textButtonColors(
						containerColor = MaterialTheme.colorScheme.tertiaryContainer,
						contentColor = MaterialTheme.colorScheme.onTertiaryContainer
					)
				) {
					Text(text = selectedItem.text, style = MaterialTheme.typography.bodyMedium)
				}
				DropdownMenu(
					expanded = menuState,
					onDismissRequest = { menuState = !menuState },
					modifier = Modifier
						.wrapContentHeight()
						.clip(MaterialTheme.shapes.medium),
					offset = DpOffset.Zero,
					properties = PopupProperties(
						dismissOnBackPress = true,
						dismissOnClickOutside = true
					)
				) {
					dropDownItems.forEach { item ->
						DropdownMenuItem(
							text = {
								Text(
									text = item.text,
									style = MaterialTheme.typography.bodySmall
								)
							},
							onClick = {
								selectedItem = item
								onReArrange(selectedItem)
							},
							colors = MenuDefaults
								.itemColors(textColor = MaterialTheme.colorScheme.onSurfaceVariant)
						)
					}
				}
			}
		},
		colors = TopAppBarDefaults
			.centerAlignedTopAppBarColors(titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
		modifier = modifier
	)
}