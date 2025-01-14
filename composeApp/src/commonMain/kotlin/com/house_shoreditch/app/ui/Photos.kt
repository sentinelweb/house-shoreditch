package com.house_shoreditch.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.house_shoreditch.app.main.MainContract
import com.moonsift.app.ui.theme.BLACK_TSP
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

object Photos {

    @Composable
    fun PhotosView(
        size: IntSize,
        model: MainContract.Model
    ) {
        var selectedPhoto by remember { mutableStateOf(-1) }
        var showLoading by remember { mutableStateOf(false) }
        val fadeSpec: FiniteAnimationSpec<Float> = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .height(size.height.dp)
        ) {
            // show photo crossfade
            Crossfade(
                targetState = selectedPhoto > -1,
                animationSpec = fadeSpec
            ) { showPhoto ->
                if (showPhoto) {
                    var showControls by remember { mutableStateOf(true) }
                    var scale by remember { mutableStateOf(1f) }
                    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
                    var job by remember { mutableStateOf<Job?>(null) }
                    val coroutineScope = rememberCoroutineScope()

                    val resetTransform = {
                        scale = 1f
                        offset = Offset(0f, 0f)
                    }
                    val incrementPhoto = { selectedPhoto = min(selectedPhoto + 1, model.imageUris.size - 1) }
                    val decrementPhoto = { selectedPhoto = max(selectedPhoto - 1, 0) }
                    val closePhoto = { selectedPhoto = -1 }
                    val startAutoHideDelay = {
                        showControls = true
                        job?.cancel()
                        job = coroutineScope.launch {
                            delay(3000)
                            showControls = false
                        }
                    }
                    val onNextPhoto = {
                        incrementPhoto()
                        resetTransform()
                        startAutoHideDelay()
                    }
                    val onPrevPhoto = {
                        decrementPhoto()
                        resetTransform()
                        startAutoHideDelay()
                    }
                    val onClosePhoto = {
                        closePhoto()
                        resetTransform()
                    }

                    LaunchedEffect(Unit) {
                        startAutoHideDelay()
                    }

                    var imageSize by remember { mutableStateOf(IntSize.Zero) }
                    var boxSize by remember { mutableStateOf(IntSize.Zero) }
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .fillMaxSize()
                            .clipToBounds()
                            .onSizeChanged { boxSize = it }
                            .pointerInput(Unit) {
                                detectTransformGestures { _, pan, zoom, _ ->
                                    val scaledImageWidth = imageSize.width * scale
                                    val scaledImageHeight = imageSize.height * scale

                                    scale *= zoom
                                    scale = max(1f, scale)

                                    offset = Offset(
                                        x = (offset.x + pan.x)
                                            .coerceIn(
                                                -(scaledImageWidth - boxSize.width) / 2f,
                                                (scaledImageWidth - boxSize.width) / 2f
                                            ),
                                        y = (offset.y + pan.y)
                                            .coerceIn(
                                                -(scaledImageHeight - boxSize.height) / 2f,
                                                (scaledImageHeight - boxSize.height) / 2f
                                            )
                                    )
                                }
                            }
                    ) {
                        // change photo crossfade
                        Crossfade(
                            targetState = selectedPhoto,
                            animationSpec = fadeSpec
                        ) { photoIndex ->
                            AsyncImage(
                                model = model.imageUris.getOrNull(photoIndex),
                                contentScale = ContentScale.Fit,
                                contentDescription = "Description",
                                onState = { state ->
                                    showLoading = when (state) {
                                        is AsyncImagePainter.State.Loading -> true
                                        else -> false
                                    }
                                },
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxSize()
                                    .graphicsLayer(
                                        scaleX = scale,
                                        scaleY = scale,
                                        translationX = offset.x,
                                        translationY = offset.y
                                    )
                                    .onSizeChanged { imageSize = it }
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onTap = {
                                                if (showControls) {
                                                    if (it.x < boxSize.width / 2) {
                                                        onPrevPhoto()
                                                    } else {
                                                        onNextPhoto()
                                                    }
                                                } else {
                                                    startAutoHideDelay()
                                                }
                                            }
                                        )
                                    }

                            )
                        }

                        Controls(
                            showControls = showControls,
                            selectedPhoto = selectedPhoto,
                            model = model,
                            onNextPhoto = onNextPhoto,
                            onPrevPhoto = onPrevPhoto,
                            onClosePhoto = onClosePhoto,
                        )

                        if (showLoading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.Center)
                                    .size(48.dp)
                            )
                        }
                    }
                } else {
                    StaggeredPhotoGrid(size, model) { selectedPhoto = it }
                }
            }
        }
    }

    @Composable
    private fun Controls(
        selectedPhoto: Int,
        showControls: Boolean,
        model: MainContract.Model,
        onNextPhoto: () -> Unit,
        onPrevPhoto: () -> Unit,
        onClosePhoto: () -> Unit,
    ) {
        val fadeSpec: FiniteAnimationSpec<Float> = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        AnimatedVisibility(
            visible = showControls, enter = fadeIn(fadeSpec), exit = fadeOut(fadeSpec)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(bottom = 84.dp)
            ) {
                Image(
                    Icons.Filled.Close,
                    contentDescription = "Close",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd)
                        .clickable { onClosePhoto() })

                if (selectedPhoto > -1) {
                    Text(
                        text = "$selectedPhoto. ${model.imageUris.getOrNull(selectedPhoto)?.imageTitle()}",
                        modifier = Modifier.align(Alignment.TopCenter)
                            .background(BLACK_TSP, shape = RoundedCornerShape(8.dp))
                            .padding(16.dp),
                        color = Color.White,
                    )
                }

                Text(
                    text = "Tap left/right side to change photo",
                    modifier = Modifier.align(Alignment.BottomCenter),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )
            }
        }
    }

    fun String.imageTitle(): String =
        substringAfterLast('/')
            .replace('_', ' ')
            .substringBeforeLast(".")
            .replaceFirstChar { it.uppercase() }

    @Composable
    fun StaggeredPhotoGrid(
        size: IntSize,
        model: MainContract.Model,
        onClickPhoto: (Int) -> Unit,
    ) {
        val numRows = 4
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(numRows),
            modifier = Modifier.height(size.height.dp),
        ) {
            items(model.imageUris.size) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        AsyncImage(
                            model = model.imageUris[item],
                            contentScale = ContentScale.Crop,
                            contentDescription = "Description",
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { onClickPhoto(item) }
                        )
                    }
                }
            }
        }
    }
}
