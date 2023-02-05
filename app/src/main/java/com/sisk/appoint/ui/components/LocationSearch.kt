package com.sisk.appoint.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import com.sisk.appoint.model.Location
import com.sisk.appoint.ui.location.LocationResults
import com.sisk.appoint.ui.location.LocationSuggestions

@Composable
fun LocationSearch(
    height: Dp = LocalConfiguration.current.screenHeightDp.dp,
    onBackPressed: () -> Unit = {},
    findLocation: suspend (String) -> List<Location> = { emptyList() },
    onLocationSelected: (Location) -> Unit ={},
    searchBarState: SearchBarState = rememberSearchBarState(),
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clickable(interactionSource = interactionSource, indication = null) {
                focusManager.clearFocus(true)
            }
    ) {
        SearchBar(
            focused = searchBarState.focused,
            query = searchBarState.query,
            onQueryChange ={
                  searchBarState.query = it
            },
            onFocusChange ={
                searchBarState.focused = it
            },
            onBackPressed = onBackPressed,
            focusRequester = focusRequester,
            focusManager = focusManager
        )
        if (searchBarState.query.text.isNotEmpty()){
            LaunchedEffect(searchBarState.query.text){
                //search
                searchBarState.isSearching = true
                searchBarState.searchResults = findLocation(searchBarState.query.text)
                searchBarState.isSearching = false
            }
        }
        when(searchBarState.searchDisplay){
            LocationSearchDisplay.NoResults -> LazyColumn{}
            LocationSearchDisplay.Results -> LocationResults(suggestions = searchBarState.searchResults){
                focusManager.clearFocus(true)
                onLocationSelected(it)
            }
            LocationSearchDisplay.Suggestions -> LocationSuggestions(suggestions = searchBarState.suggestions) {
                focusManager.clearFocus(true)
                onLocationSelected(it)
            }
        }

    }
}

@Stable
class SearchBarState(
    focused: Boolean,
    query: TextFieldValue,
    searchResults: List<Location>,
    suggestions: List<Location>,
    isSearching: Boolean
){
    var focused by mutableStateOf(focused)
    var query by mutableStateOf(query)
    var searchResults by mutableStateOf(searchResults)
    var suggestions by mutableStateOf(suggestions)
    var isSearching by mutableStateOf(isSearching)
    val searchDisplay: LocationSearchDisplay
    get() = when{
        focused && query.text.isEmpty() -> LocationSearchDisplay.Suggestions
        searchResults.isEmpty() -> LocationSearchDisplay.NoResults
        else -> LocationSearchDisplay.Results
    }
}

@Composable
fun rememberSearchBarState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searchResults: List<Location> = emptyList(),
    isSearching: Boolean = false,
    suggestions: List<Location> = emptyList()
): SearchBarState = remember {
    SearchBarState(
        focused = focused,
        query = query,
        searchResults = searchResults,
        isSearching = isSearching,
        suggestions = suggestions
    )
}

sealed class LocationSearchDisplay{
    object Suggestions: LocationSearchDisplay()
    object NoResults: LocationSearchDisplay()
    object  Results: LocationSearchDisplay()
}

@Preview(showBackground = true)
@Composable
fun LocationSearchPreview() {
    LocationSearch()
}