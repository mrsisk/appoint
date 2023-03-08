package com.sisk.appoint.ui.datetime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sisk.appoint.model.AppointDate
import com.sisk.appoint.model.WorkDayUi

@Composable
fun DatePicker(days: List<WorkDayUi>, selectedAppointDate: WorkDayUi?, onDateSelected: (WorkDayUi) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp).fillMaxWidth()
    ){
        items(days){
            DateCard(selected = it == selectedAppointDate, it){ date ->
                onDateSelected(date)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatePickerPreview() {

}