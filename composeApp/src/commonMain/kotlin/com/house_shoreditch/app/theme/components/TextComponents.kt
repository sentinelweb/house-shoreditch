package com.house_shoreditch.app.theme.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object TextComponents {
    @Composable
    fun SectionTitle(title: String) {
        Text(
            title,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
        )
    }
}
