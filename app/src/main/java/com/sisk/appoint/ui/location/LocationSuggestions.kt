package com.sisk.appoint.ui.location

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sisk.appoint.R
import com.sisk.appoint.model.Location

@Composable
fun LocationSuggestions(suggestions: List<Location> = emptyList(), onLocationClick: (Location)-> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ){
        item {
            Text(
                text = "Popular Locations",
                modifier = Modifier.padding(horizontal = 4.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }

        items(suggestions){
            Column(modifier = Modifier.clickable { onLocationClick(it) }) {
                SuggestionItem(it)
                Divider(modifier = Modifier.padding(horizontal = 12.dp))
            }

        }

    }
}

@Composable
fun SuggestionItem(location: Location) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(modifier = Modifier
            .size(50.dp)
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.inverseOnSurface),
                shape = RoundedCornerShape(20),
            )
            .background(MaterialTheme.colorScheme.outline)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .padding(12.dp)
            )
        }


        Text(text = location.name, modifier = Modifier.padding(horizontal = 4.dp), style = MaterialTheme.typography.bodyLarge)

    }
}

@Preview(showBackground = true)
@Composable
fun LocationSuggestionsPreview() {
    LocationSuggestions(suggestions = listOf(Location("Mbabane Government", longitude = 0.0, latitude = 0.0)), {})
}