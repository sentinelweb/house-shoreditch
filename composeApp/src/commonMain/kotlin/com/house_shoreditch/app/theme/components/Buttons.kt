@file:Suppress("unused","FunctionNaming", "LongParameterList", "TooManyFunctions")
package com.house_shoreditch.app.theme.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.house_shoreditch.app.theme.theme.OasisTheme
import com.house_shoreditch.app.theme.theme.RobotoFamily
import com.house_shoreditch.app.theme.theme.onSurfaceColor
import com.house_shoreditch.app.theme.theme.surfaceColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import oasis.composeapp.generated.resources.Res
import oasis.composeapp.generated.resources.star

private const val MIN_BUTTON_SCALE = 0.70f
private const val NORMAL_BUTTON_SCALE = 1f

private lateinit var themeButtonColors: ButtonColors
private lateinit var outlinedButtonColors: ButtonColors
private lateinit var outlinedBorder: BorderStroke
private lateinit var outlinedBorderDisabled: BorderStroke

private val disabledContainerColor = Color.Gray
private val disabledContentColor = Color.DarkGray

// todo remove function
@Composable
fun initButtonsColors() {
    themeButtonColors = ButtonDefaults.buttonColors(
        contentColor = surfaceColor,
        containerColor = onSurfaceColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )
    outlinedButtonColors = ButtonDefaults.buttonColors(
        contentColor = onSurfaceColor,
        containerColor = surfaceColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )
    outlinedBorder = BorderStroke(width = 1.dp, color = onSurfaceColor)
    outlinedBorderDisabled = BorderStroke(width = 0.dp, color = disabledContainerColor)
}

private const val CTA_SHAPE_PERCENT = 50
private val buttonIconSize = 24.dp
private val buttonTextStyle = TextStyle(
    fontFamily = RobotoFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
)
//private val smallButtonTextStyle = B14Primary
private val ctaButtonShape = RoundedCornerShape(CTA_SHAPE_PERCENT)
private val shadowHeight = 2.dp
private val buttonShape = RoundedCornerShape(4.dp)

@Composable
private fun iconColor(
    enabled: Boolean,
    outlined: Boolean,
) = if (enabled)
    if (outlined) onSurfaceColor else surfaceColor
else disabledContentColor

@Composable
fun RowScope.IconOrLoading(
    icon: DrawableResource,
    iconPadding: Dp = 0.dp,
    dontTintIcon: Boolean = false,
    outlined: Boolean = false,
    enabled: Boolean,
    loading: Boolean,
) {
    if (loading) {
        CircularProgressIndicator(
            color = iconColor(enabled, outlined),
            strokeWidth = 2.dp,
            modifier = Modifier
                .size(buttonIconSize)
                .align(Alignment.CenterVertically)
        )
    } else {
        ButtonIcon(icon, dontTintIcon, enabled, outlined, iconPadding)
    }
}

@Composable
private fun RowScope.ButtonIcon(
    icon: DrawableResource,
    dontTintIcon: Boolean,
    enabled: Boolean,
    outlined: Boolean,
    iconPadding: Dp
) {
    Image(
        painter = painterResource(resource = icon),
        contentDescription = "Button icon",
        colorFilter = if (!dontTintIcon) ColorFilter.tint(iconColor(enabled, outlined)) else null,
        modifier = Modifier
            .size(buttonIconSize)
            .padding(iconPadding)
            .align(Alignment.CenterVertically)
    )
}

@Composable
fun RowScope.NoIconOrLoading(
    outlined: Boolean = false,
    enabled: Boolean,
    loading: Boolean,
) {
    if (loading) {
        CircularProgressIndicator(
            color = iconColor(enabled, outlined),
            strokeWidth = 2.dp,
            modifier = Modifier
                .size(buttonIconSize)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun RoundButton(
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ButtonBase(
        onClick = { onClick() },
        outlined = false,
        enabled = enabled,
        round = true,
        text = text,
        textStartPadding = if (isLoading) 8.dp else 0.dp,
        modifier = modifier,
    ) {
        NoIconOrLoading(
            enabled = enabled,
            loading = isLoading,
            outlined = false,
        )
    }
}

@Composable
fun RoundIconButton(
    text: String,
    icon: DrawableResource,
    iconPadding: Dp = 0.dp,
    enabled: Boolean = true,
    loading: Boolean = false,
    dontTintIcon: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ButtonBase(
        onClick = { onClick() },
        outlined = false,
        enabled = enabled,
        round = true,
        text = text,
        textStartPadding = 8.dp,
        modifier = modifier,
    ) {
        IconOrLoading(
            loading = loading,
            enabled = enabled,
            outlined = false,
            icon = icon,
            iconPadding = iconPadding,
            dontTintIcon = dontTintIcon,
        )
    }
}

@Composable
fun RoundIconOutlineButton(
    text: String,
   icon: DrawableResource,
    iconPadding: Dp = 0.dp,
    enabled: Boolean = true,
    loading: Boolean = false,
    dontTintIcon: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ButtonBase(
        onClick = { onClick() },
        outlined = true,
        enabled = enabled,
        round = true,
        text = text,
        textStartPadding = 8.dp,
        modifier = modifier,
    ) {
        IconOrLoading(
            loading = loading,
            enabled = enabled,
            outlined = true,
            icon = icon,
            iconPadding = iconPadding,
            dontTintIcon = dontTintIcon,
        )
    }
}

@Composable
fun RoundOutlineButton(
    text: String,
    enabled: Boolean = true,
    loading: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ButtonBase(
        onClick = { onClick() },
        outlined = true,
        enabled = enabled,
        round = true,
        text = text,
        textStartPadding = if (loading) 8.dp else 0.dp,
        modifier = modifier,
    ) {
        NoIconOrLoading(enabled = enabled, loading = loading, outlined = true)
    }
}

@Composable
fun SquareButton(
    text: String,
    enabled: Boolean = true,
    loading: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ButtonBase(
        onClick = { onClick() },
        outlined = false,
        enabled = enabled,
        round = false,
        text = text,
        textStartPadding = if (loading) 8.dp else 0.dp,
        modifier = modifier,
    ) {
        NoIconOrLoading(enabled = enabled, loading = loading, outlined = false)
    }
}

@Composable
fun SquareOutlineButton(
    text: String,
    enabled: Boolean = true,
    loading: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ButtonBase(
        onClick = { onClick() },
        outlined = true,
        enabled = enabled,
        round = false,
        text = text,
        textStartPadding = if (loading) 8.dp else 0.dp,
        modifier = modifier,
    ) {
        NoIconOrLoading(enabled = enabled, loading = loading, outlined = true)
    }
}

@Composable
fun SquareIconButton(
    text: String,
    icon: DrawableResource,
    iconPadding: Dp = 0.dp,
    dontTintIcon: Boolean = false,
    enabled: Boolean = true,
    loading: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ButtonBase(
        onClick = { onClick() },
        outlined = false,
        enabled = enabled,
        round = false,
        text = text,
        modifier = modifier,
        textStartPadding = 8.dp
    ) {
        IconOrLoading(
            loading = loading,
            enabled = enabled,
            outlined = false,
            icon = icon,
            iconPadding = iconPadding,
            dontTintIcon = dontTintIcon,
        )
    }
}

@Composable
fun SquareIconOutlineButton(
    text: String,
    icon: DrawableResource,
    iconPadding: Dp = 0.dp,
    enabled: Boolean = true,
    loading: Boolean = false,
    dontTintIcon: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ButtonBase(
        onClick = { onClick() },
        outlined = true,
        enabled = enabled,
        round = false,
        text = text,
        modifier = modifier,
        textStartPadding = 8.dp
    ) {
        IconOrLoading(
            loading = loading,
            enabled = enabled,
            outlined = true,
            icon = icon,
            iconPadding = iconPadding,
            dontTintIcon = dontTintIcon,
        )
    }
}

private val NO_ICON: @Composable RowScope.() -> Unit = {}

@Composable
fun ButtonBase(
    text: String,
    enabled: Boolean = true,
    round: Boolean = false,
    outlined: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    textStartPadding: Dp = 0.dp,
    textStyle: TextStyle = buttonTextStyle,
    iconContent: @Composable RowScope.() -> Unit = NO_ICON,
) {
    val colors = if (outlined) outlinedButtonColors else themeButtonColors
    val border = if (outlined) {
        if (enabled) outlinedBorder else outlinedBorderDisabled
    } else null
    val shape = if (round) ctaButtonShape else buttonShape

    Button(
        onClick = { onClick() },
        colors = colors,
        border = border,
        enabled = enabled,
        shape = shape,
        modifier = modifier
            .bounceClick(enabled)
            .padding(4.dp)
            .height(48.dp)
            .shadow(if (enabled) shadowHeight else 0.dp, shape = shape)
    ) {
        Row {
            iconContent()
            Text(
                text,
                style = textStyle,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = textStartPadding)
            )
        }
    }
}

@Composable
fun CircleIconButton(
    icon: DrawableResource,
    iconPadding: Dp = 0.dp,
    enabled: Boolean = true,
    size: Dp = 48.dp,
    dontTintIcon: Boolean = false,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit,
) {
    val colors = outlinedButtonColors
    val border = outlinedBorder
    val shape = ctaButtonShape

    val colorsSelection = if (selected) {
        colors.copy(containerColor = MaterialTheme.colorScheme.secondary)
    } else {
        colors
    }

    Button(
        onClick = { onClick() },
        colors = colorsSelection,
        border = border,
        enabled = enabled,
        shape = shape,
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
            .bounceClick(enabled)
            .padding(0.dp)
            .size(size)
            .shadow(if (enabled) shadowHeight else 0.dp, shape = shape)
    ) {
        ButtonIcon(
            icon = icon,
            iconPadding = iconPadding,
            dontTintIcon = dontTintIcon,
            enabled = enabled,
            outlined = true
        )
    }
}

enum class ButtonState { Pressed, Idle }

fun Modifier.bounceClick(enabled: Boolean) = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(
        if (buttonState == ButtonState.Pressed) MIN_BUTTON_SCALE else NORMAL_BUTTON_SCALE,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
        .pointerInput(buttonState) {
            if (enabled) {
                awaitPointerEventScope {
                    buttonState = if (buttonState == ButtonState.Pressed) {
                        val event = awaitPointerEvent()
                        if (event.changes.any { it.pressed == false }) {
                            ButtonState.Idle
                        } else {
                            ButtonState.Pressed
                        }
                        ButtonState.Idle
                    } else {
                        awaitFirstDown(false)
                        ButtonState.Pressed
                    }
                }
            }
        }
}

@Preview
@Composable
private fun RoundButtonPreview() {
    OasisTheme {
        RoundButton("Button") {}
    }
}

@Preview
@Composable
private fun RoundIconButtonPreview() {
    OasisTheme {
        RoundIconButton("Button", icon = Res.drawable.star) {}
    }
}

@Preview
@Composable
private fun RoundOutlineButtonPreview() {
    OasisTheme {
        RoundOutlineButton("Button") {}
    }
}

@Preview
@Composable
private fun RoundIconOutlineButtonPreview() {
    OasisTheme {
        RoundIconOutlineButton("Button", icon = Res.drawable.star) {}
    }
}

@Preview
@Composable
private fun SquareOutlineButtonPreview() {
    OasisTheme {
        SquareOutlineButton("Button") {}
    }
}

@Preview
@Composable
private fun SquareIconButtonPreview() {
    OasisTheme {
        SquareIconButton("Button", icon = Res.drawable.star) {}
    }
}

@Preview
@Composable
private fun SquareButtonPreview() {
    OasisTheme {
        SquareButton("Button") {}
    }
}

@Preview
@Composable
private fun SquareIconOutlineButtonPreview() {
    OasisTheme {
        SquareIconOutlineButton("Button", icon = Res.drawable.star) {}
    }
}

@Preview
@Composable
private fun CircleIconButtonPreview() {
    OasisTheme {
        CircleIconButton(icon = Res.drawable.star) {}
    }
}
