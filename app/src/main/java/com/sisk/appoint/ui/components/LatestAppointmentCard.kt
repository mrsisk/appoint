package com.sisk.appoint.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LatestAppointmentCard() {
    Card(
        modifier = Modifier
            .size(width = 350.dp, height = 160.dp)
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Row {
                Surface(
                   color = MaterialTheme.colorScheme.surfaceTint,
                    modifier = Modifier
                        .width(8.dp)
                        .fillMaxHeight(),

                    ) {

                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
                ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Doctor's visit", style = MaterialTheme.typography.titleMedium)
                        Text(text = "9:00am - 10:00am", style = MaterialTheme.typography.titleMedium)
                    }
                    Text(
                        text = "Description about the appointment tt bn uio and my appointment is good  nadn and ",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(
                            vertical = 4.dp
                        )
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.DateRange, contentDescription = "")
                    Text(text = "5 Nov 2020")
                }

            }
        }

    }
}

@Preview
@Composable
fun LatestAppointmentCardPreview() {
    LatestAppointmentCard()
}