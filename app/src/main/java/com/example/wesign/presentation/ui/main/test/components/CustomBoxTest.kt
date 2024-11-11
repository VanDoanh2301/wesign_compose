package com.example.wesign.presentation.ui.main.test.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.ui.main.test.QuestionState
import com.example.wesign.presentation.ui.main.test.TestScreenEvent
import kotlin.math.absoluteValue

@Composable
fun CustomBoxTest(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    listTestQuestions: List<QuestionState>,
    event: (TestScreenEvent) -> Unit,
) {
    HorizontalPager(state = pagerState) { page ->
        val questionState = listTestQuestions[page]
        val question = questionState.question
        val answerList = questionState.answers
        val answerUser = questionState.answerUser

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            shape = WeSignShape.medium,
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = WeSignDimension.PaddingMedium)
                .graphicsLayer {
                    val pageOffset =
                        (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction

                    transformOrigin = TransformOrigin(pivotFractionX = 0.5f, pivotFractionY = 1f)
                    rotationZ = 30 * pageOffset

                    alpha = lerp(
                        start = 0f,
                        stop = 1f,
                        fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                    )
                }
                .verticalScroll(rememberScrollState())
        ) {
            QuestionCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF66cecece), shape = WeSignShape.medium
                    ),
                question =question!!.content,
                answerA = answerList[0].content ?: " ",
                answerB = answerList[1].content ?: " ",
                answerC = answerList[2].content ?: " ",
                answerD = answerList[3].content ?: " ",
                answerCorrect = questionState.answerCorrect,
                answerUser = answerUser ?: " ",
                explain = question.explanation ?: " ",
                imageLocation = question.imageLocation ?: " ",
                videoLocation = question.videoLocation ?: " "
            ) {
                event.invoke(TestScreenEvent.CheckAnswerCorrect(it, page))
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .height(6.dp)
                .background(
                    color = Color(0xFFcecece), shape = RoundedCornerShape(
                        bottomStart = 12.dp,
                        bottomEnd = 12.dp
                    )
                )) {
            }
        }

    }
}

