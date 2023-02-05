package com.sisk.appoint.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sisk.appoint.R
import com.sisk.appoint.model.Category

@Composable
fun CategoryCard(category: Category, onClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .size(height = 150.dp, width = 120.dp)
            .clickable {
                onClick(category.title)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 4.dp)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = category.image),
                contentDescription = "",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = category.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


@Preview
@Composable
fun CategoryCardPreview() {
    CategoryCard(Category(title = "Specialist", image = R.drawable.heart))
}