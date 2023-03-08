package com.sisk.appoint.ui.datetime

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sisk.appoint.model.Period
import com.sisk.appoint.model.WorkPeriod
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeCard(
    selected: Boolean = false,
    period: WorkPeriod = WorkPeriod(),
    formatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("HH:mm"),
    onTimeClick: (WorkPeriod) -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
        onClick = {
            onTimeClick(period)
        }

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 8.dp)


        ) {
            Text(text = period.start.format(formatter))
        }
    }
}

@Preview(showBackground = false)
@Composable
fun TimeCardPreview() {
    TimeCard()
}