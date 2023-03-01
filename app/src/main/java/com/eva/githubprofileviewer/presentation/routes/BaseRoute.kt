package com.eva.githubprofileviewer.presentation.routes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eva.githubprofileviewer.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var username by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val state = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = state) },
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = "Github Profile Viewer",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    when (username) {
                        "" -> scope.launch {
                            state.showSnackbar("Cannot have a blank username.Please provide a correct one")
                        }
                        else -> {
                            val userId = username.trim()
                            navController.navigate(Routes.UserInfoRoute(userId).name)
                        }
                    }
                }
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
        }

    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(R.drawable.octocat),
                contentDescription = stringResource(R.string.octocat),
                modifier = Modifier.padding(0.dp, 20.dp)
            )
            Text(text = "Enter the username to search for", textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = username,
                onValueChange = { name ->
                    username = name
                },
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                label = { Text("GitHub Username") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.None
                ),
            )
        }
    }

}