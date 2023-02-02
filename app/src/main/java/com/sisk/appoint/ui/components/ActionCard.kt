package com.sisk.appoint.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ActionCard(
    title: String,
    description: String,
    color: Color,
    onClick: ()-> Unit = {},
    icon: @Composable () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = color,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable(onClick= onClick)

    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            icon()
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = description, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActionCardPreview() {
    ActionCard(title = "Book appointment", description = "Schedule an appointment with our doctor now", color = Color(0xFFFDD835)){
        Icon(
            Icons.Outlined.DateRange,
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
        )
    }
}