package com.sisk.appoint.ui.datetime

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sisk.appoint.viewmodel.BookingViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimeScreen(
    viewModel: BookingViewModel = hiltViewModel(),
    onNavBack: () -> Unit = {}
) {
    val state  by viewModel.uiState.collectAsState()
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    LazyColumn{
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onNavBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                    Text(text = "Date and time", style = MaterialTheme.typography.titleMedium)
                }
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
                    text = "Additional information (optional)",
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
                    .padding(horizontal = 8.dp, vertical = 12.dp), onClick = { /*TODO*/ }) {
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