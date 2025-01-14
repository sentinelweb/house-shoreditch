package com.house_shoreditch.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.house_shoreditch.app.main.MainContract
import com.house_shoreditch.app.theme.components.RoundIconOutlineButton
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
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = { model.reviews.size })
        Box(
            modifier = Modifier
                .width(size.width.dp)
                .height(size.height.dp)
        ) {
            Column(modifier = Modifier) {
                SectionTitle("Reviews", modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                HorizontalPager(state = pagerState) { page ->
                    ReviewView(model.reviews.get(page), size)
                }
            }
            if (pagerState.currentPage < pagerState.pageCount - 1) {
                RoundIconOutlineButton(
                    "Next",
                    icon = Res.drawable.arrow_forward,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage((pagerState.currentPage + 1) % pagerState.pageCount)
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun ReviewView(
        review: MainContract.Model.Review,
        size: IntSize
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .size(size.width.dp, size.height.dp)
        ) {
            Column(modifier = Modifier.widthIn(max = 480.dp).align(CenterHorizontally)) {
                Text(
                    review.author,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
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
                )
            }
        }
    }
}
