package com.sisk.appoint.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.sisk.appoint.R
import kotlinx.coroutines.flow.receiveAsFlow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    viewModel: LogInViewModel = hiltViewModel(),
    navigate: () -> Unit = {}
) {

    val state by viewModel.authState.collectAsState()
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    val loader by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.ap_loading))
    val progress by animateLottieCompositionAsState(composition = loader, iterations = LottieConstants.IterateForever)

    val snackBarHostState = remember{ SnackbarHostState()}

    LaunchedEffect(key1 = viewModel.channel){
        viewModel.channel.receiveAsFlow().collect{
            snackBarHostState.showSnackbar(message = it)
        }
    }
    Scaffold(snackbarHost = {SnackbarHost(hostState = snackBarHostState)}) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 8.dp),
        )
        {

            Column(modifier = Modifier.padding(vertical = 24.dp)) {
                Text(text = "Lets sign you in", style = MaterialTheme.typography.displaySmall)
                Text(
                    text = "Welcome back, You've been missed!",
                    style = MaterialTheme.typography.titleLarge
                )
            }


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = state.email ?: "",
                    onValueChange = {
                        viewModel.onEmailChange(it)
                    },
                    placeholder = { Text(text = "Email") },
                    enabled = !state.loading
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = state.password ?: "",
                    onValueChange = {
                        viewModel.onPasswordChange(it)
                    },
                    placeholder = { Text(text = "Password") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val visibilityIcon =
                                if (passwordHidden) painterResource(id = R.drawable.visibility_24) else painterResource(id = R.drawable.visibility_off_24)
                            // Please provide localized description for accessibility services
                            val description = if (passwordHidden) "Show password" else "Hide password"
                            Icon(painter = visibilityIcon, contentDescription = description)
                        }
                    },
                    enabled = !state.loading
                )

                Button(
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                    onClick = {
                        viewModel.authenticate{
                            if(it){
                                navigate()
                            }

                        }
                    },
                    enabled = !state.loading
                ) {
                    if (state.loading) LottieAnimation(
                        composition = loader,
                        progress = { progress },
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(70.dp)
                    ) else  Text(text = "Sign in")
                }


                Text(text = "or")

                ClickableText(

                    text = AnnotatedString("Forgot password?"),
                    onClick = {

                    }
                )
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    LogInScreen()
}