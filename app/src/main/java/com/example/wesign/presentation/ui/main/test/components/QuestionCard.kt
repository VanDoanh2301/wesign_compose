package com.example.wesign.presentation.ui.main.test.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.wesign.R
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.presentation.ui.main.play.components.Player

@Composable
@Preview(showBackground = true)
fun QuestionCard(
    modifier: Modifier = Modifier,
    question: String = "",
    answerA: String = "",
    answerB: String = "",
    answerC: String = "",
    answerD: String = "",
    answerCorrect: String = "",
    answerUser: String = "",
    explain: String = "",
    imageLocation:String = "",
    videoLocation:String = "",
    onAnswerSelected: (String) -> Unit = {},
    ) {
    val options = listOf("A" to answerA, "B" to answerB, "C" to answerC, "D" to answerD)
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isAnswered by remember { mutableStateOf(false) }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(WeSignDimension.PaddingMedium)
        ) {
            if (imageLocation.isNotBlank()) {
                CustomImage(imageLocation)
            } else if (videoLocation.isNotBlank()) {
                CustomVideo(videoLocation)
            }
            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
            Text(
                text = question, style = Typography.titleMedium.copy(textAlign = TextAlign.Center),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = WeSignShape.medium)
                    .padding(WeSignDimension.PaddingLarge)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))

            if (answerUser.isNotBlank()) {
                selectedOption = answerUser
                for (index in options.indices) {
                    CustomOptionBox(
                        option = options[index].first,
                        answer = options[index].second,
                        isSelected = answerUser == options[index].second,
                        isCorrect = options[index].second == answerCorrect,
                        answerCorrect = answerCorrect,
                        selectedOption = selectedOption
                    )

                }

            } else {
                for (index in options.indices) {
                    CustomOptionBox(
                        option = options[index].first,
                        answer = options[index].second,
                        isSelected = selectedOption == options[index].second,
                        isCorrect = selectedOption == answerCorrect,
                        answerCorrect = answerCorrect,
                        selectedOption = selectedOption,
                        onClick = {
                            if (!isAnswered) {
                                selectedOption = options[index].second
                                onAnswerSelected(options[index].second)
                                isAnswered = true
                            }

                        }
                    )

                }
            }
            if (explain.isNotBlank()) {
                if (isAnswered || answerUser.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))
                    CustomExplain(explain)
                }
            }
        }

    }
}

@Composable
fun CustomVideo(videoLocation: String) {
    Player(videoLocation)
}

@Composable
fun CustomImage(imageLocation: String) {
    AsyncImage(
        model = imageLocation,
        contentDescription = stringResource(R.string.app_name),
        modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f).background(Color.White, shape = WeSignShape.medium),
    )
}

@Composable
fun CustomExplain(explain: String) {
    var isShowExplain by remember { mutableStateOf(true) }
    var textShow by remember { mutableStateOf(TXT_HIDE_ANSWER) }

    val density = LocalDensity.current
    Column {
        TextButton(
            onClick = {
                isShowExplain = !isShowExplain
                textShow = if (!isShowExplain) TXT_HIDE_ANSWER else TXT_SHOW_ANSWER
            },
            modifier = Modifier.background(
                color = Color.White,
                shape = WeSignShape.medium
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_explain),
                    contentDescription = TXT_SHOW_ANSWER,
                    tint = primaryLight
                )
                Spacer(modifier = Modifier.width(WeSignDimension.PaddingMedium))
                Text(
                    text = textShow, style = Typography.titleMedium
                )
                Spacer(modifier = Modifier.width(WeSignDimension.PaddingMedium))
                Icon(
                    Icons.Default.ArrowRight,
                    contentDescription = TXT_SHOW_ANSWER,
                    tint = primaryLight,
                    modifier = Modifier.size(32.dp)

                )
            }
        }

        AnimatedVisibility(false, enter = slideInVertically {
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            expandFrom = Alignment.Bottom
        ) + fadeIn(
            initialAlpha = 0.8f
        ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()) {
            Column {
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = WeSignShape.medium)
                ) {
                    Text(
                        text = explain, style = Typography.titleMedium,
                        modifier = Modifier.padding(WeSignDimension.PaddingMedium)
                    )
                }
            }

        }
    }

}

const val TXT_HIDE_ANSWER = "Ẩn giải thích"
const val TXT_SHOW_ANSWER = "Hiện giải thích"