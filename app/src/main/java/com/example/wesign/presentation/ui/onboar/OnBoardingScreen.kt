package com.example.wesign.presentation.ui.onboar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.ui.onboar.components.CustomIndicators
import com.example.wesign.presentation.ui.onboar.components.OnBoardItem
import kotlinx.coroutines.launch


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OnBoardingScreen(
    event: (OnBoardingScreenEvent) -> Unit = {},
    onBoardingFinished: () -> Unit = {}
) {
    val statePager = rememberPagerState(0, 0F) { 3 }
    val scope = rememberCoroutineScope()
    val buttonText by remember {
        derivedStateOf {
            when (statePager.currentPage) {
                0 -> {
                    listOf("Lùi lại", "Tiếp")
                }

                1 -> {
                    listOf("Lùi lại", "Tiếp")
                }

                else -> {
                    listOf("", "Bắt đầu")
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(state = statePager) { page ->
                ItemOnBoarding(buttonText, listOnBoarding[page], page, onNext = {
                    scope.launch {
                        if (statePager.currentPage == 2) {
                            event(OnBoardingScreenEvent.setFirstApp(false))
                            onBoardingFinished()
                            return@launch
                        }
                        statePager.animateScrollToPage(statePager.currentPage + 1)
                    }
                }, onSkip = {
                    scope.launch {
                        statePager.animateScrollToPage(statePager.currentPage - 1)
                    }
                })
            }
        }
    }

}

@Composable
fun ItemOnBoarding(
    buttonText: List<String>,
    onBoardItem: OnBoardItem,
    page: Int,
    onSkip: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(onBoardItem.image),
                contentScale = ContentScale.Crop
            ), contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WeSignDimension.PaddingExtraLarge),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(WeSignDimension.PaddingLarge),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = onBoardItem.title,
                    style = Typography.headlineSmall.copy(
                        textAlign = TextAlign.Center, fontFamily = FontFamily(
                            Font(R.font.inter_bold)
                        )
                    )
                )
                Text(
                    text = onBoardItem.description,
                    style = Typography.labelMedium.copy(
                        textAlign = TextAlign.Center, fontFamily = FontFamily(
                            Font(R.font.inter_regular)
                        )
                    ),
                    modifier = Modifier.padding(top = WeSignDimension.PaddingLarge)
                )
                Row(modifier = Modifier.padding(top = WeSignDimension.PaddingLarge)) {
                    repeat(3) {
                        CustomIndicators(isSelected = page == it)
                        Spacer(modifier = Modifier.width(WeSignDimension.PaddingMedium))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = WeSignDimension.PaddingLarge),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    if (buttonText[0].isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .padding(WeSignDimension.PaddingLarge)
                                .weight(1f), contentAlignment = Alignment.BottomStart
                        ) {
                            Button(
                                onClick = {
                                    onSkip()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                ),
                            ) {
                                Text(
                                    text = buttonText[0],
                                    style = Typography.titleSmall.copy(
                                        color = Color(0xFF757575),
                                        fontFamily = FontFamily(Font(R.font.inter_bold))
                                    ),
                                    modifier = Modifier.padding(WeSignDimension.PaddingMedium)
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .padding(WeSignDimension.PaddingLarge)
                            .background(
                                color = if (buttonText[0].isNotEmpty()) Color(
                                    0xFF5F61F0
                                ) else Color(
                                    0xFF5F61F0
                                ), shape = WeSignShape.medium
                            )
                            .clickable {
                                onNext()
                            }
                            .weight(1f),
                        contentAlignment = if (buttonText[0].isNotEmpty()) Alignment.Center else Alignment.Center

                    ) {

                        Text(
                            text = buttonText[1],
                            style = Typography.titleSmall,
                            color = Color.White,
                            modifier = Modifier
                                .padding(vertical = WeSignDimension.PaddingLarge)


                        )
                    }
                }
            }
        }
    }
}


val listOnBoarding = listOf(
    OnBoardItem(
        R.drawable.onboarding_page_1,
        "Học ngôn ngữ ký hiệu dễ dàng",
        "WeSign giúp bạn tiếp cận ngôn ngữ ký hiệu tiếng Việt một cách dễ dàng và hiệu quả qua ứng dụng và trang web."
    ),
    OnBoardItem(
        R.drawable.onboarding_page_2,
        "Hỗ trợ cho người khiếm thính",
        "WeSign cung cấp học liệu phong phú, phù hợp cho người điếc, trẻ khiếm thính, và cả phụ huynh muốn học ngôn ngữ ký hiệu."
    ),
    OnBoardItem(
        R.drawable.onboarding_page_3,
        "Cùng phát triển cộng đồng",
        "Chúng tôi không ngừng cải tiến WeSign với sự đóng góp từ các tình nguyện viên và cộng đồng người học."
    )
)
