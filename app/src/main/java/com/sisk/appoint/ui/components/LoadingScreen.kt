package com.sisk.appoint.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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

@Composable
fun LoadingScreen() {
    val loader by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loader_color))
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
                modifier = Modifier.size(150.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}