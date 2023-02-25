package com.eva.githubprofileviewer.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eva.githubprofileviewer.domain.models.GitHubUserModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssociatedData(
    model: GitHubUserModel,
    modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.surfaceVariant),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Contacts", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            model.twitterUserName?.let {
                Row {
                    Icon(imageVector = Icons.Default.Key, contentDescription = "Twitter username")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = model.twitterName, color = MaterialTheme.colorScheme.primary)
                }
            }

            model.websiteUrl?.let {
                Row {
                    Icon(imageVector = Icons.Default.Web, contentDescription = "Twitter username")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = model.websiteUrl)
                }
            }
            model.email?.let {
                Row {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Twitter username")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = model.email)
                }
            }
        }
    }
}