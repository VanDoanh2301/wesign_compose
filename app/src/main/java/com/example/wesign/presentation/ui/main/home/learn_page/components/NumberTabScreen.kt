package com.example.wesign.presentation.ui.main.home.learn_page.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.gradient1
import com.example.wesign.presentation.theme.gradient2
import com.example.wesign.presentation.theme.gradient3
import com.example.wesign.presentation.theme.gradient4
import com.example.wesign.presentation.theme.gradient5

@Composable
@Preview(showSystemUi = true)
fun NumberScreen(onClickNumber: (String) -> Unit = {}) {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val colors = listOf(gradient3, gradient4)
    LazyColumn {
        items(numbers.size) { number ->
            CardItemNumber(number = numbers[number].toString(), colors.shuffled().first()) {
                onClickNumber(numbers[number].toString())
            }
        }
    }
}

@Composable
fun AlphabetTabScreen(onClickAlphabet: (String) -> Unit = {}) {
    val alphabet = ('A'..'Z').toList()
    val colors = listOf(gradient1, gradient2, gradient3, gradient4, gradient5)
    LazyColumn {
        items(alphabet.size) { number ->
            CardItemNumber(number = alphabet[number].toString(), colors.shuffled().first()) {
                onClickAlphabet(alphabet[number].toString())
            }
        }
    }
}

@Composable
fun CardItemNumber(number: String, gradient: List<Color>, onClickItem: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = WeSignDimension.PaddingMedium,
                start = WeSignDimension.PaddingMedium,
                end = WeSignDimension.PaddingMedium
            )
            .border(2.dp, gradient[0],shape = WeSignShape.medium )
        ,
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        ), onClick = {
            onClickItem()
        }
    ) {
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .height(76.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        brush = Brush.linearGradient(
                            gradient
                        ), shape = RoundedCornerShape(
                            topStart = 12.dp,
                            bottomStart = 12.dp
                        )
                    ),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_alphabet),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentHeight()
                        .alpha(0.4f),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    Icons.Filled.PlayCircle,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
                    .background(
                        color = Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(
                            topEnd = 12.dp,
                            bottomEnd = 12.dp
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = WeSignDimension.PaddingLarge),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Number: $number", style = Typography.titleLarge.copy(
                            fontFamily = FontFamily(
                                Font((R.font.inter_medium))
                            )
                        ), textAlign = TextAlign.Start, color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))
                    Text(
                        text = "Provide by teacher",
                        style = Typography.titleSmall.copy(
                            fontFamily = FontFamily(Font((R.font.inter_regular))),
                            textAlign = TextAlign.Start, color = Color.Black
                        )
                    )
                }
            }
        }
    }
}
