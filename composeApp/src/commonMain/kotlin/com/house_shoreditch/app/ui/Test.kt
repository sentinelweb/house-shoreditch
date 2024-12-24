package com.house_shoreditch.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.house_shoreditch.app.theme.components.*
import com.house_shoreditch.app.theme.components.TextComponents.Hr
import osric.composeapp.generated.resources.*

object Test {

    @Composable
    fun TestContent() {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Surface", style = MaterialTheme.typography.titleSmall)
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surface))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.onSurface))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceContainer))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceVariant))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceDim))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceTint))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceBright))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceContainerLowest))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceContainerLow))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceContainerHigh))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceContainerHighest))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.inverseSurface))
            }
            Text("Primary", style = MaterialTheme.typography.titleSmall)
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.primary))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.onPrimary))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.primaryContainer))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.onPrimaryContainer))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.inversePrimary))
            }
            Text("Secondary || Tertiary", style = MaterialTheme.typography.titleSmall)
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.secondary))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.onSecondary))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.secondaryContainer))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.onSecondaryContainer))
                Text("||", style = MaterialTheme.typography.titleSmall)
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.tertiary))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.onTertiary))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.tertiaryContainer))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.onTertiaryContainer))
            }
            Text("background || error || outline || scrim", style = MaterialTheme.typography.titleSmall)
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.background))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.onBackground))
                Text("||", style = MaterialTheme.typography.titleSmall)
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.error))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.onError))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.errorContainer))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.onErrorContainer))
                Text("||", style = MaterialTheme.typography.titleSmall)
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.outline))
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.outlineVariant))
                Text("||", style = MaterialTheme.typography.titleSmall)
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.scrim))


            }
            Hr()
            TextComponents.SectionTitle("SectionTitle")
            TextComponents.SubSectionTitle("SubSectionTitle")
            TextComponents.BodyText("BodyText")
            TextComponents.LabelText("LabelText")

            Hr()
            RoundIconOutlineButton(
                "RoundIconOutlineButton",
                icon = Res.drawable.calendar,
                onClick = { }
            )
            RoundOutlineButton(
                "RoundOutlineButton",
                onClick = { }
            )
            RoundIconButton(
                "RoundIconButton",
                icon = Res.drawable.crypto_btc,
                onClick = { }
            )
            RoundButton(
                "RoundButton",
                onClick = { }
            )
            SquareIconOutlineButton(
                "SquareIconOutlineButton",
                icon = Res.drawable.pounds,
                onClick = { }
            )
            SquareOutlineButton(
                "SquareOutlineButton",
                onClick = { }
            )
            SquareIconButton(
                "SquareIconButton",
                icon = Res.drawable.crypto_eth,
                onClick = { }
            )
            SquareButton(
                "SquareButton",
                onClick = { }
            )
            Row{
                CircleIconButton(icon = Res.drawable.pounds, modifier = Modifier.padding(4.dp)) {}
                CircleIconButton(icon = Res.drawable.cash_payment, selected = true, modifier = Modifier.padding(4.dp)) {}
                CircleIconButton(icon = Res.drawable.credit_card, modifier = Modifier.padding(4.dp)) {}
                CircleIconButton(icon = Res.drawable.crypto_btc, selected = true, modifier = Modifier.padding(4.dp)) {}

            }
        }
    }
}
