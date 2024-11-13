package com.example.wesign.presentation.ui.main.test.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.gradient3
import com.example.wesign.presentation.theme.primaryLight


@Composable
@Preview(showBackground = true)
fun QuestionHeaderCard(
    modifier: Modifier = Modifier,
    currentQuestion: Int = 1,
    totalQuestions: Int = 10,
    onPrevious: () -> Unit = {},
    onNext: () -> Unit = {},
) {
    Card(
        shape = WeSignShape.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(WeSignDimension.PaddingMedium)
            .clip(shape = WeSignShape.medium)
            .clickable() {

            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            primaryLight,
                            primaryLight.copy(alpha = 0.5f)
                        )
                    ), shape = WeSignShape.medium
                )
                .shadow(
                    elevation = 1.dp,
                    shape = WeSignShape.medium,
                    spotColor =   primaryLight.copy(alpha = 0.5f),
                    clip = true
                )
        ) {
            IconButton(onClick = onPrevious) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = "Previous",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(WeSignDimension.PaddingMedium))
            Text(
                text = "Câu hỏi $currentQuestion/$totalQuestions",
                style = Typography.titleMedium
            )
            Spacer(modifier = Modifier.width(WeSignDimension.PaddingMedium))

            IconButton(onClick = onNext) {
                Icon(
                    Icons.Default.ArrowForwardIos,
                    contentDescription = "Previous",
                    tint = Color.White
                )
            }
        }
    }

}
