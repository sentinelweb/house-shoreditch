package com.house_shoreditch.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.house_shoreditch.app.main.MainContract
import com.house_shoreditch.app.main.MainContract.BookingModel
import com.house_shoreditch.app.main.MainContract.PaymentMethod
import com.house_shoreditch.app.main.MainContract.PaymentMethod.*
import com.house_shoreditch.app.main.MainViewModel
import com.house_shoreditch.app.theme.components.CircleIconButton
import com.house_shoreditch.app.theme.components.RoundIconOutlineButton
import com.house_shoreditch.app.theme.components.TextComponents.Hr
import com.house_shoreditch.app.theme.components.TextComponents.LabelText
import com.house_shoreditch.app.theme.components.TextComponents.SubSectionTitle
import org.jetbrains.compose.resources.stringResource
import osric.composeapp.generated.resources.*

object Booking {

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun BookingSection(
        size: IntSize,
        viewModel: MainViewModel
    ) {
        val bookingModel = viewModel.bookingModel.collectAsState()
        var showDatePicker by remember { mutableStateOf(false) }
        var selectedDateRange: Pair<Long?, Long?> by remember { mutableStateOf(null to null) }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
                .height(size.height.dp)
                .padding(horizontal = 16.dp)
        ) {

            //SectionTitle("Booking")

            //BodyText("You can book on the major booking sites or try booking directly to save unnecessary fees :)")

            SubSectionTitle("Direct booking")

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                RoundIconOutlineButton(
                    "Select dates",
                    icon = Res.drawable.calendar,
                    onClick = { showDatePicker = true }
                )

                Text(
                    bookingModel.value.dateRange.first + " -> " + bookingModel.value.dateRange.second,
                )

                if (showDatePicker) {
                    DateRangePickerModal(
                        onDateRangeSelected = {
                            selectedDateRange = it
                            showDatePicker = false
                        },
                        onDismiss = { showDatePicker = false }
                    )
                }
            }

            LabelText("Preferred payment methods:")
            PaymentMethodsRow(bookingModel.value) { method -> }

            LabelText("Send enquiry via ..")

            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                RoundIconOutlineButton(
                    "Email",
                    icon = Res.drawable.email,
                    onClick = { }
                )
                RoundIconOutlineButton(
                    "Gmail",
                    icon = Res.drawable.google,
                    onClick = { }
                )
                RoundIconOutlineButton(
                    "SMS",
                    icon = Res.drawable.sms,
                    onClick = { }
                )
            }


            Hr()

            SubSectionTitle("Or book on ...")

            FlowRow(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                RoundIconOutlineButton(
                    "Airbnb",
                    icon = Res.drawable.airbnb_com,
                    onClick = { viewModel.openAirbnb() }
                )
                RoundIconOutlineButton(
                    "Booking.com",
                    icon = Res.drawable.booking_com,
                    onClick = { viewModel.openBooking() }
                )

            }
        }
    }

    @Composable
    fun PaymentMethodsRow(bookingModel: BookingModel, onClick: (PaymentMethod) -> Unit) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            PaymentMethod(Pounds, bookingModel, onClick)
            PaymentMethod(Cash, bookingModel, onClick)
            PaymentMethod(CreditCard, bookingModel, onClick)
            PaymentMethod(Btc, bookingModel, onClick)
            PaymentMethod(PayPal, bookingModel, onClick)
            PaymentMethod(Eth, bookingModel, onClick)
            PaymentMethod(Sol, bookingModel, onClick)
            PaymentMethod(Doge, bookingModel, onClick)
            PaymentMethod(Ltc, bookingModel, onClick)
        }
    }

    @Composable
    private fun PaymentMethod(method: PaymentMethod, bookingModel: BookingModel, onClick: (PaymentMethod) -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(4.dp).clickable { onClick(method) }
        ) {
            CircleIconButton(
                icon = method.drawable,
                selected = bookingModel.paymentMethods.contains(method),
            ) { onClick(Pounds) }
            Text(stringResource(method.description), style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(4.dp))
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DateRangePickerModal(
        onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
        onDismiss: () -> Unit
    ) {
        val dateRangePickerState = rememberDateRangePickerState()

        DatePickerDialog(
            modifier = Modifier.background(Color.Transparent),
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        onDateRangeSelected(
                            Pair(
                                dateRangePickerState.selectedStartDateMillis,
                                dateRangePickerState.selectedEndDateMillis
                            )
                        )
                        onDismiss()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text("OK", color = MaterialTheme.colorScheme.onSurface)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    )
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DateRangePicker(
                state = dateRangePickerState,
                title = {
                    Text(
                        text = "Select date range",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                showModeToggle = false,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    selectedYearContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedYearContainerColor = MaterialTheme.colorScheme.onSurface,
                    selectedDayContentColor = MaterialTheme.colorScheme.surface,
                    selectedDayContainerColor = MaterialTheme.colorScheme.onSurface,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.surface,
                    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.onSurface,
                    todayContentColor = MaterialTheme.colorScheme.onSurface,
                    todayDateBorderColor = MaterialTheme.colorScheme.secondary,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )
        }
    }

}
