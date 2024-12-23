package com.house_shoreditch.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.house_shoreditch.app.theme.components.*
import com.house_shoreditch.app.theme.components.TextComponents.Hr
import osric.composeapp.generated.resources.*

object Test {

    @Composable
    fun TestContent(size: IntSize) {
        Column(
            Modifier
                .fillMaxWidth()
//                .height(size.height.dp)
                .padding(16.dp)
        ) {
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
