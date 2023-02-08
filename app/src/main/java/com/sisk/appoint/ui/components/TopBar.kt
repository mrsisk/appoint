package com.sisk.appoint.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sisk.appoint.R
import com.sisk.appoint.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    user: User,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
) {
    TopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier) {

            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.profile),
                contentDescription = ""
            )
            Text(
                text = user.username,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.wrapContentSize(unbounded = true).padding(horizontal = 8.dp)
            )

        }
    }, actions = {
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Localized description"
            )
        }
    },
        scrollBehavior = scrollBehavior
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(User(username = "John", email = "sisk@gmail.com", image = "") )
}