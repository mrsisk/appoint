package com.sisk.appoint.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sisk.appoint.R

@Composable
fun LocationCard(onClick: ()-> Unit) {
    Card(modifier = Modifier.padding(horizontal = 16.dp).clickable { onClick() }) {
       Box(modifier = Modifier.height(250.dp).fillMaxWidth()) {
          Image(
              painter = painterResource(id = R.drawable.find_location),
              contentDescription = "",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Fit
          )
       }
    }
}

@Preview
@Composable
fun LocationCardPreview() {
    LocationCard {}
}