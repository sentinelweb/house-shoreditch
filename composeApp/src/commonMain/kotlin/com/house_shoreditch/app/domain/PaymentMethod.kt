package com.house_shoreditch.app.domain

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import osric.composeapp.generated.resources.*

enum class PaymentMethod(
    val drawable: DrawableResource,
    val description: StringResource,
    ) {
        Pounds(Res.drawable.pounds, Res.string.pm_pounds),
        Cash(Res.drawable.cash_payment, Res.string.pm_cash),
        CreditCard(Res.drawable.credit_card, Res.string.pm_cc),
        Btc(Res.drawable.crypto_btc, Res.string.pm_btc),
        Eth(Res.drawable.crypto_eth, Res.string.pm_eth),
        Doge(Res.drawable.crypto_doge, Res.string.pm_doge),
        PayPal(Res.drawable.paypal, Res.string.pm_Paypal),
        Sol(Res.drawable.crypto_sol, Res.string.pm_sol),
        Ltc(Res.drawable.crypto_litecoin, Res.string.pm_ltc)
    }
