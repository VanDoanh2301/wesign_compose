package com.example.wesign.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.presentation.ui.auth.success.LottieAnimationCustom

@Composable
@Preview(showBackground = true)
fun CustomLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .size(160.dp)
                .shadow(elevation = 1.dp, shape = WeSignShape.medium)
                .background(color = Color.White, shape = WeSignShape.medium)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimationCustom(
                modifier = Modifier
                    .size(120.dp),
                resId = R.raw.animation, // Replace with your Lottie file
                autoPlay = true,
                loop = true
            )
            Text(
                text = "Loading...", color = primaryLight, style = Typography.titleMedium.copy(
                    fontFamily = FontFamily(
                        Font(R.font.inter_bold)
                    ),
                    textAlign = TextAlign.Center
                ),

            )
        }

    }
}