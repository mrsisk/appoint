package com.sisk.appoint.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.sisk.appoint.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorScreen(
    message: String = "ERROR",
    onRetry: () -> Unit = {}
) {
    val loader by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lazy_cat))
    val progress by animateLottieCompositionAsState(
        composition = loader,
        iterations = LottieConstants.IterateForever
    )
    Surface {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = loader,
                progress = { progress },
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
            )
            Text(text = message, modifier = Modifier.padding(horizontal = 8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text(text = "Retry")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen()
}