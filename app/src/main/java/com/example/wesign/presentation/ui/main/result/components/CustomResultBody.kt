package com.example.wesign.presentation.ui.main.result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.theme.ErrorColor
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.errorLight


const val PROGRESS_STROKE = 24
const val PROGRESS_SIZE = 160
const val BOX_BORDER_WIDTH = 1

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CustomResultBody(
    countCorrect: Int = 3,
    totalQuestions: Int = 10
) {

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(WeSignDimension.PaddingMedium)
                .background(
                    color = Color.White,
                    shape = WeSignShape.medium
                ),
            contentAlignment = Alignment.Center
        ) {
            val progressLoadTest = countCorrect.toFloat() / totalQuestions.toFloat()
            CustomCircularProgress(progress = progressLoadTest, text = "${countCorrect}\nĐúng", percentSuccess = "${countCorrect * 100 / totalQuestions} %")
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(WeSignDimension.PaddingMedium)
                .background(
                    color = Color.White,
                    shape = WeSignShape.medium
                ),
            contentAlignment = Alignment.Center
        ) {
            CustomDisplayDetailResult(
                correctCount = countCorrect,
                incorrectCount = totalQuestions - countCorrect,
                totalQuestions = totalQuestions
            )
        }
    }

}

@Composable
fun CustomDisplayDetailResult(
    correctCount: Int,
    incorrectCount: Int,
    totalQuestions: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(WeSignDimension.PaddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Correct Count Box
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(
                        BOX_BORDER_WIDTH.dp, Color(0xFF008000),WeSignShape.medium
                    )
                    .padding(WeSignDimension.PaddingMedium),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = correctCount.toString(),
                        style = Typography.headlineSmall.copy(color = Color(0xFF008000) )
                    )
                    Text(
                        text = "Đúng",
                        style = Typography.titleMedium.copy(color = Color(0xFF008000) )
                    )
                }
            }

            Spacer(modifier = Modifier.width(WeSignDimension.PaddingMedium))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(
                        BOX_BORDER_WIDTH.dp, ErrorColor, WeSignShape.medium
                    )
                    .padding(WeSignDimension.PaddingMedium),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = incorrectCount.toString(),
                        style = Typography.headlineSmall.copy(color = errorLight )
                    )
                    Text(
                        text = "Không đúng",
                        style = Typography.titleMedium.copy(color = errorLight )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total Questions Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BOX_BORDER_WIDTH.dp, Color.Black, WeSignShape.medium)
                .padding(WeSignDimension.PaddingMedium),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = totalQuestions.toString(),
                    style = Typography.headlineSmall.copy(color = Color.Black)
                )
                Text(
                    text = "Tổng câu hỏi",
                   style = Typography.titleMedium.copy(color = Color.Black)
                )
            }
        }
    }
}

@Composable
fun CustomCircularProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    trackColor: Color = Color(0xFFED6F71),
    progressColor: Color = Color(0xFF009955),
    strokeWidth: Dp = PROGRESS_STROKE.dp,
    text: String = "03\nCorrect",
    percentSuccess:String = "20%"
) {
    Column(
        modifier = modifier.padding(WeSignDimension.PaddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.size(PROGRESS_SIZE.dp),
                color = progressColor,
                strokeWidth = strokeWidth,
                trackColor = trackColor,
            )

            Text(
                text = text ,
                style = Typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))
        Text(
            text = "Chúc mừng", style = Typography.titleLarge.copy(fontFamily = FontFamily(Font(R.font.inter_bold)))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Bạn đã hoàn thành $percentSuccess", style = Typography.titleMedium
        )
    }

}


