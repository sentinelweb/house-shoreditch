package com.house_shoreditch.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.house_shoreditch.app.main.MainContract
import com.house_shoreditch.app.main.MainViewModel
import com.house_shoreditch.app.theme.components.RoundOutlineButton
import com.house_shoreditch.app.theme.components.TextComponents.SectionTitle

object Booking {

    @Composable
    fun BookingSection(
        size: IntSize,
        model: MainContract.Model,
        viewModel: MainViewModel
    ) {
        var showDatePicker by remember { mutableStateOf(false) }
        var selectedDateRange: Pair<Long?, Long?> by remember { mutableStateOf(null to null) }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .height(size.height.dp)
                .padding(16.dp)
        ) {

            SectionTitle("Booking")

            RoundOutlineButton(
                "Booking.com",
                onClick = { viewModel.openBooking() }
            )

            RoundOutlineButton(
                "Airbnb",
                onClick = { viewModel.openAirbnb() }
            )
            RoundOutlineButton(
                "Select range",
                onClick = { showDatePicker = true }
            )

            Text(
                (selectedDateRange.first?.toString() ?: "?") + " -> " + (selectedDateRange.second?.toString() ?: "?"),
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
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DateRangePickerModal(
        onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
        onDismiss: () -> Unit
    ) {
        val dateRangePickerState = rememberDateRangePickerState()

        DatePickerDialog(
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
                        text = "Select date range"
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

                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(16.dp)
            )
        }
    }

}
