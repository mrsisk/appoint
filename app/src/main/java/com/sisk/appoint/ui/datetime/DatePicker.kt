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
import com.sisk.appoint.model.AppointDateTime

@Composable
fun DatePicker(dateTime: List<AppointDateTime>, selectedAppointDateTime: AppointDateTime, onDateSelected: (AppointDateTime) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp).fillMaxWidth()
    ){
        items(dateTime){
            DateCard(selected = it == selectedAppointDateTime, it){date ->
                onDateSelected(date)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatePickerPreview() {

}