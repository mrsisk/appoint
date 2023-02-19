package com.sisk.appoint.ui.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.sisk.appoint.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(onSelectOption: (AuthenticationMode) -> Unit = {}, lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {

//    DisposableEffect(key1 = lifecycleOwner){
//        val observer = LifecycleEventObserver{_, event ->
//            when(event){
//                Lifecycle.Event.ON_CREATE -> Log.d("mama s", "onCreate")
//                Lifecycle.Event.ON_START -> Log.d("mama s", "onStart")
//                Lifecycle.Event.ON_RESUME -> Log.d("mama s", "onResume")
//                Lifecycle.Event.ON_PAUSE -> Log.d("mama s", "onPause")
//                Lifecycle.Event.ON_STOP -> Log.d("mama s", "onStop")
//                Lifecycle.Event.ON_DESTROY -> Log.d("mama s", "onDestroy")
//                Lifecycle.Event.ON_ANY -> Log.d("mama s", "ANY")
//            }
//
//        }
//        lifecycleOwner.lifecycle.addObserver(observer)
//
//        // When the effect leaves the Composition, remove the observer
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }
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