package com.house_shoreditch.app.ui

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.house_shoreditch.app.BuildProps
import com.house_shoreditch.app.main.MainContract
import com.house_shoreditch.app.main.MainViewModel
import com.house_shoreditch.app.theme.components.CircleIconButton
import com.house_shoreditch.app.theme.components.RoundIconOutlineButton
import com.house_shoreditch.app.theme.components.TextComponents.SectionTitle
import com.house_shoreditch.app.theme.components.TextComponents.SubSectionTitle
import com.house_shoreditch.app.util.DateUtil
import com.house_shoreditch.app.util.getPlatform
import oasis.composeapp.generated.resources.*
import kotlin.math.max

object Contact {
    @Composable
    fun ContactSection(
        size: IntSize,
        model: MainContract.Model,
        viewModel: MainViewModel
    ) {
        val contactModel = viewModel.contactModel.collectAsState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(size.height.dp)
                .padding(16.dp)
        ) {
            SectionTitle("Contact")
            var offset by remember { mutableStateOf(Offset(200f, 400f)) }
            var scale by remember { mutableStateOf(1f) }
            var imageSize by remember { mutableStateOf(IntSize.Zero) }
            var boxSize by remember { mutableStateOf(IntSize.Zero) }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .widthIn(max = 600.dp)
                    .height(300.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onSizeChanged { boxSize = it }
            ) {
                AsyncImage(
                    model = model.mapImageUri,
                    contentScale = ContentScale.None,
                    contentDescription = "Description",
                    modifier = Modifier
                        .wrapContentSize(unbounded = true) // this fixed it
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offset.x,
                            translationY = offset.y
                        )
                        .pointerInput(Unit) {
                            detectTransformGestures { _, pan, zoom, _ ->
                                val scaledImageWidth = imageSize.width * scale
                                val scaledImageHeight = imageSize.height * scale
                                val oscale = scale
                                scale *= zoom
                                scale = max(1f, scale)
                                offset = Offset(
                                    x = (offset.x + pan.x * oscale)
                                        .coerceIn(
                                            -(scaledImageWidth - boxSize.width) / 2f,
                                            (scaledImageWidth - boxSize.width) / 2f
                                        ),
                                    y = (offset.y + pan.y * oscale)
                                        .coerceIn(
                                            -(scaledImageHeight - boxSize.height) / 2f,
                                            (scaledImageHeight - boxSize.height) / 2f
                                        )
                                )
                            }
                        }
                        .onSizeChanged { imageSize = it }
                )
            }
            RoundIconOutlineButton(
                contactModel.value.phone ?: "Phone",
                icon = Res.drawable.phone,
                onClick = { viewModel.onContactPhoneClick() }
            )
            RoundIconOutlineButton(
                contactModel.value.email ?: "Email",
                icon = Res.drawable.email,
                onClick = { viewModel.onContactEmailClick() }
            )
            if (getPlatform().isWhatsappAvailable) {
                RoundIconOutlineButton(
                    "WhatsApp",
                    icon = Res.drawable.whatsapp,
                    onClick = { viewModel.onContactWhatsAppClick() }
                )
            }

            SubSectionTitle("Other platforms", modifier = Modifier.padding(vertical = 8.dp))
            Row(modifier = Modifier.horizontalScroll(rememberScrollState(0))) {
                CircleIconButton(
                    icon = Res.drawable.os_play_store,
                    onClick = { viewModel.onDownloadPlayClick() },
                    modifier = Modifier.padding(4.dp)
                )
                CircleIconButton(
                    icon = Res.drawable.os_apple_store,
                    onClick = { viewModel.onDownloadAppleStoreClick() },
                    modifier = Modifier.padding(4.dp)
                )
                CircleIconButton(
                    icon = Res.drawable.os_mac,
                    onClick = { viewModel.onDownloadMacClick() },
                    modifier = Modifier.padding(4.dp)
                )
                CircleIconButton(
                    icon = Res.drawable.os_windows,
                    onClick = { viewModel.onDownloadWinClick() },
                    modifier = Modifier.padding(4.dp)
                )
                CircleIconButton(
                    icon = Res.drawable.os_linux,
                    onClick = { viewModel.onDownloadLinuxClick() },
                    modifier = Modifier.padding(4.dp)
                )
                CircleIconButton(
                    icon = Res.drawable.os_html,
                    onClick = { viewModel.onDownloadWebClick() },
                    modifier = Modifier.padding(4.dp)
                )
            }

            Text(
                text = "©Robert Munro ${DateUtil.year} - version: ${BuildProps.versionName}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
