package com.sisk.appoint.ui.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sisk.appoint.model.Location

@Composable
fun LocationResults(suggestions: List<Location> = emptyList(), onLocationClick: (Location) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ){

        items(suggestions){
            Column(modifier = Modifier.clickable { onLocationClick(it) }) {
                SuggestionItem(it)
                Divider(modifier = Modifier.padding(horizontal = 12.dp))
            }
        }

    }
}