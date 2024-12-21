package com.house_shoreditch.app

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.house_shoreditch.app.theme.components.MoonCtaIconButton
import com.house_shoreditch.app.theme.components.TextComponents.SectionTitle
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import osric.composeapp.generated.resources.Res
import osric.composeapp.generated.resources.arrow_forward
import osric.composeapp.generated.resources.star

object Reviews {

    @Composable
    fun ReviewsSection(
        size: IntSize,
        model: MainContract.Model,
    ) {
        val horizontalScrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        val density = LocalDensity.current
        Box(
            modifier = Modifier
                .width(size.width.dp)
                .height(size.height.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                SectionTitle("Reviews")
                Row(
                    modifier = Modifier
                        .horizontalScroll(horizontalScrollState)
                        .padding(end = 32.dp)
                ) {
                    model.reviews.forEach { review ->
                        ReviewView(review, size.width)
                    }
                }
            }
            MoonCtaIconButton(
                "Next",
                icon = Res.drawable.arrow_forward,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 84.dp),
                onClick = {
                    coroutineScope.launch {
                        val currentPage = horizontalScrollState.value / (size.width - 64) / density.density
                        println("currentPage: $currentPage")
                        horizontalScrollState.animateScrollTo(((size.width - 64) * density.density * (currentPage+1)).toInt())
                    }
                }
            )
        }
    }

    private @Composable
    fun ReviewView(
        review: MainContract.Model.Review,
        width: Int
    ) {
        Column(
            modifier = Modifier
                .width((width - 64).dp)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                review.author,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)) {
                (1..review.rating).forEach { _ ->
                    Icon(
                        painterResource(Res.drawable.star),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(16.dp)
                    )
                }
            }
            Text(
                review.review.replace("\n", "\n\n"),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .verticalScroll(rememberScrollState(0))
            )
        }
    }
}
