package com.house_shoreditch.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.house_shoreditch.app.main.MainContract
import com.house_shoreditch.app.main.MainViewModel
import com.house_shoreditch.app.theme.components.TextComponents.SectionTitle

object Contact {
    @Composable
    fun ContactSection(
        size: IntSize,
        model: MainContract.Model
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .height(size.height.dp)
                .padding(16.dp)
        ) {
            SectionTitle("Contact")
            AsyncImage(
                model = model.mapImageUri,
                contentScale = ContentScale.Crop,
                contentDescription = "Description",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(400.dp)
            )

        }
    }


}
