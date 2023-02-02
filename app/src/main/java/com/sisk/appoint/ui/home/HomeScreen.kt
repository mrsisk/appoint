package com.sisk.appoint.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sisk.appoint.model.categories
import com.sisk.appoint.ui.components.*


@Composable
fun HomeScreen(navHostController: NavHostController) {

    LazyColumn(modifier = Modifier.fillMaxWidth()){
//        item {
//            TopBar(user = User(username = "John sisk", email = "sisk@gmail.com", image = ""))
//        }

        item {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
                Text(
                    text = "Health tip for the day",
                    style = MaterialTheme.typography.titleLarge,

                )
                Text(text = "Learn more about health tips, and stay safe", style = MaterialTheme.typography.labelMedium)
            }

        }

        item {
            BannerCard()
        }
        item {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            )
        }
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(horizontal = 12.dp)){
                items(categories){
                    CategoryCard(it)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }


        item {
            Text(
                text = "Shortcuts",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            )
        }
        item {
            ActionCard(title = "SOS", description = "Call our emergency number to report to us", color = MaterialTheme.colorScheme.primary
            ){
                Icon(
                    Icons.Outlined.Call,
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
        item {
            ActionCard(title = "Call the office", description = "Give us a call in order for any enquiries", color = MaterialTheme.colorScheme.secondary
            ){
                Icon(
                    Icons.Outlined.Phone,
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }

        item {
            Text(
                text = "Looking for a closest hospital?",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            )
        }

        item {
            LocationCard(){
                navHostController.navigate("location")
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
  //  HomeScreen()
}