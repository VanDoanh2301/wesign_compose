package com.example.wesign.presentation.ui.auth.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.wesign.R
import com.example.wesign.presentation.theme.DarkGreen
import com.example.wesign.presentation.theme.Green
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight

const val CONGRATS_TEXT = "Congratulations!"
const val ACCOUNT_CREATED_TEXT = "Your account has been successfully created."
const val GO_TO_LOGIN_TEXT = "Go to Login"

@Composable
@Preview(showBackground = true)
fun SuccessScreen(onHomeClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ) {
//        IconButton(onClick = { }) {
//            Icon(Icons.Filled.ArrowBack, contentDescription = "")
//        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimationCustom(
                modifier = Modifier
                    .size(200.dp),
                resId = R.raw.success, // Replace with your Lottie file
                autoPlay = true,
                loop = true
            )

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            Text(
                text = CONGRATS_TEXT,
                style = Typography.headlineSmall.copy(
                    color = Color(0xFF00e676),
                    fontFamily = FontFamily(Font(R.font.inter_bold))
                ),
            )
            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
            Text(
                text = ACCOUNT_CREATED_TEXT,
                style = Typography.titleSmall.copy(
                    fontFamily = FontFamily(Font(R.font.inter_bold))
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingExtraLarge))

            Button(
                onClick = { onHomeClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryLight,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = WeSignShape.small
            ) {
                Text(text = GO_TO_LOGIN_TEXT, style = Typography.titleSmall)
            }
        }
    }
}

@Composable
fun LottieAnimationCustom(
    modifier: Modifier = Modifier,
    resId: Int,
    autoPlay: Boolean = false,
    loop: Boolean = true
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
    val animationState = animateLottieCompositionAsState(
        composition = composition,
        isPlaying = autoPlay,
        iterations = if (loop) LottieConstants.IterateForever else 1
    )

    LottieAnimation(
        composition = composition,
        progress = { animationState.progress },
        modifier = modifier,
        contentScale = ContentScale.FillWidth
    )


}