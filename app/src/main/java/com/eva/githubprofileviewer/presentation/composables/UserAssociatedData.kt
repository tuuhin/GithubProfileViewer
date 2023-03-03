package com.eva.githubprofileviewer.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.R
import com.eva.githubprofileviewer.domain.models.GitHubUserModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssociatedData(
    model: GitHubUserModel,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(10.dp).fillMaxWidth()
        ) {
            Text(text = "Contacts", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            model.twitterUserName?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.twitter),
                        contentDescription = "Twitter username"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = model.twitterName, color = MaterialTheme.colorScheme.primary)
                }
            }

            model.websiteUrl?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Link, contentDescription = "Twitter username")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = model.websiteUrl)
                }
            }
            model.email?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Twitter username")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = model.email)
                }
            }
        }
    }
}