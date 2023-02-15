package com.sisk.appoint.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sisk.appoint.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(onSelectOption: (AuthenticationMode) -> Unit = {}) {
    Scaffold {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
               // Text(text = "Welcome to APPOINT", style = MaterialTheme.typography.displaySmall)
                Image(
                    painter = painterResource(id = R.drawable.work),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier

                        .padding(vertical = 8.dp)
                )
              //  Text(text = "Join the number 1 trusted platform", style = MaterialTheme.typography.titleSmall)
            }
            
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    onSelectOption(AuthenticationMode.SIGN_IN)
                }) {
                    Text(text = "Sign in")
                }


                OutlinedButton(modifier = Modifier.fillMaxWidth(),onClick = {
                    onSelectOption(AuthenticationMode.SIGN_UP)
                }) {
                    Text(text = "Create account")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}