package com.sisk.appoint.ui.datetime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.sisk.appoint.model.Period
import com.sisk.appoint.model.WorkPeriod
import com.sisk.appoint.ui.components.Header


@Composable
fun TimeComponent(
    heading: String = "Morning",
    periods: List<WorkPeriod> = emptyList(),
    selectedPeriod: WorkPeriod? = WorkPeriod(),
    onTimeClick: (WorkPeriod)-> Unit = {}
) {
    Column {
        Header(title = heading)
        FlowRow(
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 8.dp,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            periods.forEach {
                TimeCard(period = it, selected = it == selectedPeriod, onTimeClick = onTimeClick)
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun TimeComponentPreview() {
    TimeComponent(heading = "Morning", periods = listOf())
}