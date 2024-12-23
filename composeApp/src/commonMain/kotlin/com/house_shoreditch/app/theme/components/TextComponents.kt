package com.house_shoreditch.app.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object TextComponents {
    @Composable
    fun SectionTitle(title: String, modifier: Modifier = Modifier) {
        Text(
            title,
            style = MaterialTheme.typography.displayMedium,
            modifier = modifier
        )
    }

    @Composable
    fun SubSectionTitle(title: String, modifier: Modifier = Modifier) {
        Text(
            title,
            style = MaterialTheme.typography.displaySmall,
            modifier = modifier
        )
    }

    @Composable
    fun LabelText(title: String, modifier: Modifier = Modifier) {
        Text(
            title,
            style = MaterialTheme.typography.labelMedium,
            modifier = modifier
        )
    }

    @Composable
    fun BodyText(title: String, modifier: Modifier = Modifier) {
        Text(
            title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(vertical = 16.dp),
        )
    }

    @Composable
    fun Hr(){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurface)
        )
    }
}
