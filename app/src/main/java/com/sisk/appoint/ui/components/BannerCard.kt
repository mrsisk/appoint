package com.sisk.appoint.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BannerCard() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
        .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
       Row(
           modifier = Modifier
           .fillMaxWidth()
           .fillMaxHeight().padding(horizontal = 16.dp),
           verticalAlignment = Alignment.CenterVertically,

       ) {
           Column(modifier = Modifier.weight(1f)) {
               Text(text = "Covid-19", style = MaterialTheme.typography.titleLarge)
               Text(
                   text = "Protect yourself from Covid-19 the virus by washing your hands everyday",
                   style = MaterialTheme.typography.bodyMedium,
                   maxLines = 2,
                   overflow = TextOverflow.Ellipsis
               )
               Button(modifier = Modifier.padding(vertical = 4.dp), onClick = { /*TODO*/ }) {
                   Text(text = "Learn more")
               }
           }

           Image(
               painter = painterResource(id = com.sisk.appoint.R.drawable.doctor),
               contentDescription = "",
               contentScale = ContentScale.Fit,
               modifier = Modifier.size(100.dp)
           )
       }
    }
}

@Preview
@Composable
fun BannerCardPreview() {
    BannerCard()
}