package com.sisk.appoint.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Header(
    title: String = "Title",
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
) {
    Text(
        text = title,
        style = textStyle,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header()
}