package com.eva.githubprofileviewer.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.eva.githubprofileviewer.presentation.UserInfoViewModel
import com.eva.githubprofileviewer.presentation.composables.RepositoryCard
import com.eva.githubprofileviewer.utils.RepositoryArrangement
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRepositoryInformation(
    modifier: Modifier = Modifier,
    userInfoViewModel: UserInfoViewModel = koinViewModel()
) {
    var menuState by remember { mutableStateOf(false) }
    var selectedItem by remember{
        mutableStateOf<RepositoryArrangement>(RepositoryArrangement.BySize)
    }

    val dropDownItems = remember {
        arrayOf(
            RepositoryArrangement.BySize,
            RepositoryArrangement.ByFork,
            RepositoryArrangement.ByStars
        )
    }

    val state = userInfoViewModel.repositoryState.value
    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Top Repositories",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
                Column(
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .wrapContentHeight()
                            .clickable { menuState = !menuState }
                    ) {
                        Box(
                            modifier = Modifier.padding(16.dp,8.dp)
                        ) {
                            Text(
                                text = selectedItem.text,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = menuState,
                        onDismissRequest = { menuState = !menuState },
                        modifier = Modifier.wrapContentHeight(),
                        offset = DpOffset.Zero,
                        properties = PopupProperties(
                            dismissOnBackPress = true, dismissOnClickOutside = true
                        )
                    ) {
                        for (item in dropDownItems) {
                            DropdownMenuItem(text = {
                                Text(
                                    text = item.text, color = MaterialTheme.colorScheme.primary
                                )
                            }, onClick = {
                                selectedItem = item
                                userInfoViewModel.rearrangeRepository(selectedItem)
                            })
                        }

                    }
                }
            }

            state.content?.let { repos ->
                LazyColumn {
                    items(repos.size) { idx ->
                        val model = repos[idx]
                        RepositoryCard(repositoryModel = model)
                    }
                }
            }
        }

    }
}

