package com.sisk.appoint.ui.datetime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sisk.appoint.model.AppointDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateCard(selected: Boolean, appointDate: AppointDate, onDateClick: (AppointDate) -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onDateClick(appointDate)
        },
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .width(80.dp),
        tonalElevation = 4.dp,
        shadowElevation = 6.dp

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 18.dp)
        ) {
            Text(text = appointDate.dayOfWeek, textAlign = TextAlign.Center)
            Text(text = appointDate.dayOfMonth, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DateCardPreview() {
   // DateCard(selected  = false)
}