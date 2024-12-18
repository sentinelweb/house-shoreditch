package com.house_shoreditch.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

object Facilities {


    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun FacilitiesScreen(
        size: IntSize,
        model: MainContract.Model,
    ) {
        val verticalScrollState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .height(size.height.dp)
                .verticalScroll(verticalScrollState)
                .padding(16.dp)
        ) {
            Text(
                "Facilities",
                style = MaterialTheme.typography.displayMedium,
            )
            FlowRow(modifier = Modifier.padding(8.dp)) {
                model.areas.forEach { area ->
                    Area(area)
                }
            }
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    private fun Area(area: MainContract.Model.Area) {
        Column(
            modifier = Modifier.widthIn(max = 508.dp)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Icon(
                    painterResource(area.icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = 8.dp).size(32.dp)
                )
                Text(
                    area.name,
                    style = MaterialTheme.typography.displaySmall,
                )
            }
            val splitFeatures = area.features.chunked(6)
            FlowRow {
                splitFeatures.forEach { features ->
                    Column { features.forEach { feature -> AreaFeature(feature) } }
                }
            }
        }

    }

    @Composable
    private fun AreaFeature(feature: MainContract.Model.Area.Feature) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(236.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                painterResource(feature.icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(horizontal = 8.dp,).size(24.dp)
            )
            Text(
                feature.title,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
