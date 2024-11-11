package com.example.wesign.presentation.ui.main.test.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wesign.R
import com.example.wesign.presentation.theme.WarningColor
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.light_blue_a700

@Composable
@Preview(showBackground = true)
fun CustomDrawerItemTest(
    question: String = "1",
    answerA: String = "",
    answerB: String = "",
    answerC: String = "",
    answerD: String = "",
    answerCorrect: String = "",
    answerUser: String = "",
    isCurrentQuestion: Boolean = false,
    onClick: () -> Unit = {}
) {
    val options = listOf("A" to answerA, "B" to answerB, "C" to answerC, "D" to answerD)
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .border(
                    if (isCurrentQuestion) {
                        BorderStroke(1.dp, Color.Black)
                    } else {
                        BorderStroke(0.dp, Color.Transparent)
                    }, shape = WeSignShape.medium)
                .padding(vertical = WeSignDimension.PaddingSmall, horizontal = WeSignDimension.PaddingSmall)
                .background(color = WarningColor, shape = WeSignShape.medium)
                .padding(horizontal =WeSignDimension.PaddingSmall, vertical = WeSignDimension.PaddingSmall)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = LocalIndication.current
                ) {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            color = Color.White,
                            shape = WeSignShape.medium
                        ),
                    contentAlignment = Alignment.Center

                ) {
                    CustomTextView(text = question, Modifier.padding(horizontal = 8.dp, vertical = 6.dp))
                }
                Spacer(Modifier.width(WeSignDimension.PaddingSmall))
                options.forEachIndexed { index, option ->
                    Box(contentAlignment = Alignment.Center) {
                        Column {
                            CustomTextView(
                                text = options[index].first,
                                modifier = Modifier
                            )
                            Spacer(Modifier.width(WeSignDimension.PaddingSmall))

                            Box(
                                modifier = Modifier.border(
                                    BorderStroke(1.dp, light_blue_a700)
                                )
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_select),
                                    contentDescription = "",
                                    modifier = Modifier.size(12.dp)
                                        .alpha(
                                            if (options[index].second == answerUser) {
                                                1f
                                            } else {
                                                0f
                                            }
                                        )
                                )
                            }

                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CustomTextView(text: String, modifier: Modifier = Modifier) {
    BasicText(
        text = text,
        modifier = modifier.padding(2.dp),
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 12.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )
    )
}