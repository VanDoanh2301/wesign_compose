package com.example.wesign.presentation.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.decode.ImageSource
import com.example.wesign.R
import com.example.wesign.presentation.component.ImageBackgroundColorScrim
import com.example.wesign.presentation.theme.WeSignDimension
import kotlinx.coroutines.delay

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SplashScreen(onSplashFinished: () -> Unit = {}) {
    var loading by remember { mutableStateOf(false) }
    var textVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        loading = true
        delay(600)
        textVisible = true
        delay(2000)
        onSplashFinished()

    }
    val alpha by animateFloatAsState(  // Start loading and text visibility
        targetValue = if (textVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.img_splash), contentScale = ContentScale.Crop),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = "",
                modifier = Modifier.size(220.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "WE SIGN",
                style =  com.example.wesign.presentation.theme.Typography.headlineLarge,
                color = Color.White,
                modifier = Modifier
                    .alpha(alpha)
                    .padding(top = WeSignDimension.PaddingExtraLarge)
            )
            if (loading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = WeSignDimension.PaddingExtraLarge),
                    color = Color.White,
                    trackColor = Color(0xFF5F61F0)
                )
            }

        }

    }

}