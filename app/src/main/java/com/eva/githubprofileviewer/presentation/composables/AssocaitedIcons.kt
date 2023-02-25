package com.eva.githubprofileviewer.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import java.text.NumberFormat

@Composable
fun AssociatedIcons(
    icon: ImageVector,
    count: Int,
    label: String,
    modifier: Modifier = Modifier,
    contentDescription: String = "Icons description",
) {

    val format = NumberFormat.getInstance()
    val countReadable = format.format(count)
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        Icon(icon, contentDescription)

        Column(
            modifier = Modifier.padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = countReadable)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, style = MaterialTheme.typography.bodySmall)
        }
    }

}