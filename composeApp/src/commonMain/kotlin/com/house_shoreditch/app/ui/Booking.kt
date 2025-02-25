package com.house_shoreditch.app.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.house_shoreditch.app.domain.PaymentMethod
import com.house_shoreditch.app.domain.PaymentMethod.*
import com.house_shoreditch.app.main.MainContract.BookingModel
import com.house_shoreditch.app.main.MainViewModel
import com.house_shoreditch.app.theme.components.CircleIconButton
import com.house_shoreditch.app.theme.components.RoundIconOutlineButton
import com.house_shoreditch.app.theme.components.TextComponents.FormTitle
import com.house_shoreditch.app.theme.components.TextComponents.Hr
import com.house_shoreditch.app.theme.components.TextComponents.SubSectionTitle
import com.house_shoreditch.app.util.getPlatform
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import oasis.composeapp.generated.resources.*

object Booking {

    @Composable
    fun BookingSection(
        size: IntSize,
        viewModel: MainViewModel
    ) {
        val bookingModel = viewModel.bookingModel.collectAsState()
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
                .height(size.height.dp)
                .padding(horizontal = 16.dp)
        ) {

            SubSectionTitle("Direct booking")

            bookingModel.value.error?.let {
                ErrorDisplay(it)
            }

            DateSelectionField(bookingModel, viewModel)

            NumberPeopleField(bookingModel, viewModel)

            PaymentMethods(bookingModel, viewModel)

            SendButtons(viewModel)

            Hr()

            BookOnSection(viewModel)
        }
    }

    private @Composable
    fun ErrorDisplay(error: String) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.errorContainer, RoundedCornerShape(50))
                .border(1.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(50))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                error,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }

    @Composable
    @OptIn(ExperimentalLayoutApi::class)
    private fun DateSelectionField(
        bookingModel: State<BookingModel>,
        viewModel: MainViewModel
    ) {
        var showDatePicker by remember { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            RoundIconOutlineButton(
                "Select dates",
                icon = Res.drawable.calendar,
                onClick = { showDatePicker = true },
                modifier = Modifier.padding(end = 8.dp)
            )

            bookingModel.value.dateRange?.let {
                FlowRow {
                    Text(it.first)
                    Icon(
                        painterResource(Res.drawable.arrow_forward),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp).size(16.dp)
                    )
                    Text(it.second)
                }
            }

            if (showDatePicker) {
                DateRangePickerModal(
                    onDateRangeSelected = {
                        viewModel.onDatesSelected(it)
                        showDatePicker = false
                    },
                    onDismiss = { showDatePicker = false }
                )
            }
        }
    }

    @Composable
    private fun NumberPeopleField(
        bookingModel: State<BookingModel>,
        viewModel: MainViewModel
    ) {
        FormTitle(
            "Number of people:",
            modifier = Modifier
        )
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp)) {
            CircleIconButton(
                icon = Res.drawable.minus,
            ) {
                viewModel.onChangeNumPeople(bookingModel.value.numPeople - 1)
            }

            Text(
                bookingModel.value.numPeople.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            CircleIconButton(
                icon = Res.drawable.add,
            ) {
                viewModel.onChangeNumPeople(bookingModel.value.numPeople + 1)
            }
        }
    }

    @Composable
    private fun PaymentMethods(
        bookingModel: State<BookingModel>,
        viewModel: MainViewModel
    ) {
        FormTitle(
            "Preferred payment methods:",
            modifier = Modifier
        )
        PaymentMethodsField(bookingModel.value) { method ->
            viewModel.onClickPayemntMethod(method)
        }
    }

    @Composable
    fun PaymentMethodsField(bookingModel: BookingModel, onClick: (PaymentMethod) -> Unit) {
        Row(
            modifier = Modifier
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
            ) { onClick(method) }
            Text(
                stringResource(method.description),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(4.dp)
            )
        }
    }

    @Composable
    private fun SendButtons(viewModel: MainViewModel) {
        FormTitle("Send enquiry via ..")
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            if (getPlatform().isGmailAvailable) {
                CircleIconButton(
                    icon = Res.drawable.google,
                    onClick = { viewModel.onSendEnquiryGmail() },
                    modifier = Modifier.padding(4.dp)
                )
            }
            if (getPlatform().isEmailAvailable) {
                CircleIconButton(
                    icon = Res.drawable.email,
                    onClick = { viewModel.onSendEnquiryEmail() },
                    modifier = Modifier.padding(4.dp)
                )
            }
            if (getPlatform().isSmsAvailable) {
                CircleIconButton(
                    icon = Res.drawable.sms,
                    onClick = { viewModel.onSendEnquirySms() },
                    modifier = Modifier.padding(4.dp)
                )
            }
            if (getPlatform().isWhatsappAvailable) {
                CircleIconButton(
                    icon = Res.drawable.whatsapp,
                    onClick = { viewModel.onSendEnquiryWhatsApp() },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DateRangePickerModal(
        onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
        onDismiss: () -> Unit
    ) {
        val dateRangePickerState = rememberDateRangePickerState(
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis > Clock.System.now().toEpochMilliseconds()
                }

                override fun isSelectableYear(year: Int): Boolean {
                    return year >= Clock.System.now().epochSeconds / (60 * 60 * 24 * 365)
                }
            }
        )

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

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    private fun BookOnSection(viewModel: MainViewModel) {
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
