package com.sisk.appoint.ui.datetime

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sisk.appoint.ui.components.ToolBar
import com.sisk.appoint.ui.viewmodel.BookingViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimeScreen(
    viewModel: BookingViewModel = hiltViewModel(),
    onNavBack: () -> Unit = {},
    onNavigateToReview: () -> Unit = {}
) {
    val state  by viewModel.uiState.collectAsState()
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    LazyColumn(modifier = Modifier.statusBarsPadding()){

        item {
            ToolBar(
                title = "Date and time",
                stage = "2/3",
                onNavBack = onNavBack
            )
        }

            item {
                Text(
                    text = "Appointment date",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                )
            }
            item {
                DatePicker(state.days, selectedAppointDate = state.appointDate){
                    viewModel.updateAppointDate(it)
                }
            }


        item {
            TimeComponent(
                heading = "Morning",
                periods = state.morningPeriods,
                selectedPeriod = state.appointPeriod,
                onTimeClick = {
                    viewModel.updateAppointPeriod(it)
                }
            )
        }

        item {
            TimeComponent(
                heading = "Afternoon",
                periods = state.afternoonPeriods,
                selectedPeriod = state.appointPeriod,
                onTimeClick = {
                    viewModel.updateAppointPeriod(it)
                }
            )
        }


        item {
                Text(
                    text = "Additional information (optional) ${state.location.name}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
                )
            }
            item {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .height(100.dp),
                    singleLine = false,
                    maxLines = 4,
                    value = text,
                    onValueChange = {
                                    text = it
                    },

                )
            }

            item {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp), onClick = onNavigateToReview) {
                    Text(text = "Proceed")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DateTimeScreenPreview() {
    DateTimeScreen()
}