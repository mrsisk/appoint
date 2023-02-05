package com.sisk.appoint.ui.location

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sisk.appoint.R
import com.sisk.appoint.viewmodel.BookingViewModel
import com.sisk.appoint.ui.components.LocationSearch
import com.sisk.appoint.ui.components.rememberSearchBarState
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationScreen(
    viewModel: BookingViewModel = hiltViewModel(),
    navigateToDateTime: () -> Unit = {},
    onNavBack: () -> Unit = {}
) {
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
        animationSpec = tween(300)
    )
    val state by viewModel.uiState.collectAsState()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val height = LocalConfiguration.current.screenHeightDp.dp
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = FocusRequester()
    BackHandler(enabled = sheetState.isExpanded) {
        coroutineScope.launch {
            sheetState.collapse()
        }
    }


    BottomSheetScaffold(
        backgroundColor = MaterialTheme.colorScheme.surface,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        scaffoldState = bottomSheetScaffoldState,
        sheetElevation = 10.dp,
        sheetPeekHeight = (-56).dp,
        sheetShape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 20.dp,
            topEnd = 20.dp
        ),
        sheetContent = {
            LocationSearch(
                height = height,
                onBackPressed = {
                    coroutineScope.launch {
                        sheetState.collapse()
                    }
                },
                focusRequester = focusRequester,
                onLocationSelected = {
                    viewModel.updateLocation(it)
                    coroutineScope.launch {
                        sheetState.collapse()
                    }
                },
                findLocation = {
                    viewModel.searchLocation(it)
                },
                searchBarState = rememberSearchBarState(
                    suggestions = state.locationSuggestions
                )
            )
        }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
        ){
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp), verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onNavBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                    Text(text = "Location", modifier = Modifier.padding(horizontal = 4.dp), style = MaterialTheme.typography.bodyLarge)
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(278.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.locate),
                        contentDescription = "",
                        modifier = Modifier.size(278.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)) {

                    Spacer(modifier = Modifier.height(8.dp))
                    // Text(text = "Step 1", style = MaterialTheme.typography.titleMedium)
                    Text(buildAnnotatedString {
                        append("Are you going to ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)){
                            append(state.location.name)
                        }
                        append("?")
                    }, style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),onClick = {
                    navigateToDateTime()
                }) {
                    Text(text = "Yes")
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),onClick = {
                    coroutineScope.launch {
                        sheetState.expand()
                        focusRequester.requestFocus()
                    }
                }) {
                    Text(text = "No")
                }
                Spacer(modifier = Modifier
                    .height(8.dp)
                    .padding(horizontal = 8.dp))
                Text(
                    text = "Please note suggested location is auto-detected based on current your location",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            }


        }
    }


}

@Preview
@Composable
fun LocationScreenPreview() {
    LocationScreen()
}