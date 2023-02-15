package com.sisk.appoint.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sisk.appoint.ui.components.*
import com.sisk.appoint.ui.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    onCategorySelected: (String) -> Unit = {},
    viewHomeModel: HomeViewModel = hiltViewModel()
) {
    val state by viewHomeModel.uiState.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxWidth()){

        item {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
                Header(title = "Health tip for the day", modifier = Modifier)

            }

        }

        item {
            BannerCard(info = state.info)
        }
        item {
            Header(title = "Categories")
        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ){
                items(state.categories){
                    CategoryCard(it){id ->
                        onCategorySelected(id)
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }


        item {
            Header(title = "Shortcuts")
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
            Header(title = "Looking for a closest hospital?")
        }

        item {
            LocationCard{
                //TODO
                viewHomeModel.test()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}