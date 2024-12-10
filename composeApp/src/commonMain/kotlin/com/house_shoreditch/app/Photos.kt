package com.house_shoreditch.app

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
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
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
        var selectedPhoto by remember { mutableStateOf<Int>(-1) }
        var showLoading by remember { mutableStateOf(false) }
        val fadeSpec: FiniteAnimationSpec<Float> = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .height(size.height.dp)
        ) {
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
                    val incrementPhoto = { selectedPhoto = min(selectedPhoto + 1, model.images.size - 1) }
                    val decrementPhoto = { selectedPhoto = max(selectedPhoto - 1, 0) }
                    val closePhoto = { selectedPhoto = -1 }

                    val startAutoHideDelay = {
                        showControls = true
                        job?.cancel() // Cancel any existing job
                        job = coroutineScope.launch {
                            delay(3000) // 3 seconds delay
                            showControls = false
                        }
                    }
                    val state = rememberTransformableState { zoomChange, panChange, _ ->
                        scale *= zoomChange
                        offset = Offset(
                            offset.x + panChange.x,
                            offset.y + panChange.y
                        )
                    }
                    LaunchedEffect(Unit) {
                        startAutoHideDelay()
                    }

                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTransformGestures { _, pan, zoom, _ ->
                                    scale *= zoom
                                    offset = Offset(
                                        offset.x + pan.x,
                                        offset.y + pan.y
                                    )
                                }

                            }
                    ) {
                        Crossfade(
                            targetState = selectedPhoto,
                            animationSpec = fadeSpec
                        ) { photoIndex ->
                            AsyncImage(
                                model = model.images.getOrNull(photoIndex),
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
                                    .transformable(state = state)
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onTap = {
                                                startAutoHideDelay()
                                            }
                                        )
                                    }
                            )
                        }

                        androidx.compose.animation.AnimatedVisibility(
                            visible = showControls, enter = fadeIn(fadeSpec), exit = fadeOut(fadeSpec)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    Icons.Filled.Close,
                                    contentDescription = "Close",
                                    colorFilter = ColorFilter.tint(Color.White),
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.TopEnd)
                                        .clickable { closePhoto(); resetTransform(); })

                                if (selectedPhoto < model.images.size - 1) {
                                    Image(
                                        Icons.Filled.ArrowCircleRight,
                                        contentDescription = "Next",
                                        colorFilter = ColorFilter.tint(Color.White),
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .size(32.dp)
                                            .align(Alignment.CenterEnd)
                                            .background(BLACK_TSP, shape = RoundedCornerShape(4.dp))
                                            .clickable {
                                                incrementPhoto()
                                                resetTransform()
                                                startAutoHideDelay()
                                            })

                                }

                                if (selectedPhoto > 0) {
                                    Image(
                                        Icons.Filled.ArrowCircleLeft,
                                        contentDescription = "Previous",
                                        colorFilter = ColorFilter.tint(Color.White),
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .size(32.dp)
                                            .align(Alignment.CenterStart)
                                            .background(BLACK_TSP, shape = RoundedCornerShape(4.dp))
                                            .clickable {
                                                decrementPhoto()
                                                resetTransform()
                                                startAutoHideDelay()
                                            })
                                }
                            }
                        }

                        if (showLoading) {
                            CircularProgressIndicator(
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
//
//    @Composable
//    private fun BoxScope.ControlsOverlay(
//        selectedPhoto: Int,
//        model: MainContract.Model,
//        incrementPhoto: () -> Unit,
//        resetTransform: () -> Unit,
//        decrementPhoto: () -> Unit,
//        closePhoto: () -> Unit,
//    ) {
//
//    }

    @Composable
    fun StaggeredPhotoGrid(
        size: IntSize,
        model: MainContract.Model,
        onClickPhoto: (Int) -> Unit,
    ) {
        val isLandscape = size.width > size.height
        val numColumns = max(3, (size.width / 150f).toInt())
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(numColumns),
            modifier = Modifier.height(size.height.dp),
        ) {
            items(model.images.size) { item ->
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
                            model = model.images[item],
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
