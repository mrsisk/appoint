package com.sisk.appoint.ui.datetime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TimePicker() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp).fillMaxWidth()
    ){
        items(7){
            TimeCard(it == 0)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimePickerPreview() {
    TimePicker()
}