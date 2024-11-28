package com.example.wesign.presentation.ui.main.home.home_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.theme.PurpleStart
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.gradient1
import com.example.wesign.presentation.theme.gradient2
import com.example.wesign.presentation.theme.gradient3
import kotlinx.coroutines.delay

const val TITLE_1 = "Học ngôn ngữ\nkí hiệu"
const val TITLE_2 = "Sử dụng AI để nhận dạng\nkí hiệu tay"
const val TITLE_3 = "Xem những video\nhọc tập hữu ích"
const val CHAT_TITLE = "Trò chuyện với bạn bè"
const val CHAT_CONTENT = "Bắt đầu trò chuyện ngay"
const val VIDEO_TITLE = "Chia sẻ video"
const val VIDEO_CONTENT = "Chia sẻ video cho chúng tôi"

@Composable
fun SlideContent() {
    val items = listOf(
        ItemSlide(
            title = TITLE_1,
            content = "IBME lab",
            icon = Icons.Default.Public,
            iconColor = gradient1[0],
            gradient = gradient1
        ),
        ItemSlide(
            title = TITLE_2,
            content = "IBME lab",
            icon = Icons.Default.Public,
            iconColor = gradient2[0],
            gradient = gradient2
        ),
        ItemSlide(
            title = TITLE_3,
            content = "IBME lab",
            icon = Icons.Default.Public,
            iconColor = gradient3[0],
            gradient = gradient3
        )
    )
    val pagerState = rememberPagerState(
        0, 0F
    ) {items.size}

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }
    HorizontalPager(state = pagerState) { page ->
        CardContent(item = items[page])
    }

    Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
//    ChatSupportCard(
//        title = CHAT_TITLE,
//        content = CHAT_CONTENT,
//        image = R.drawable.chat_service,
//        color = Color(0xFFdde2ff)
//    )
//    Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
//
//    ChatSupportCard(
//        title =  VIDEO_TITLE,
//        content = VIDEO_CONTENT,
//        image = R.drawable.video_update,
//        color = Color(0xFFf1a566)
//    )

}

@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    item: ItemSlide
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = WeSignDimension.PaddingMedium)
            .background(
                brush = Brush.linearGradient(
                    item.gradient
                ), shape = WeSignShape.medium
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(WeSignDimension.PaddingLarge)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = item.title,
                    style = Typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))
                Text(
                    text = item.content,
                    style = Typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.width(WeSignDimension.PaddingLarge))
            Icon(
                imageVector = item.icon,
                contentDescription = "",
                tint = item.iconColor,
                modifier = Modifier.size(46.dp)
            )
        }
    }
}

@Composable
fun ChatSupportCard(
    title: String = "Chat with Friends",
    content: String = "Start a conversation now",
    image: Int = R.drawable.chat_service,
    color: Color = Color(0xFFdde2ff)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        shape = WeSignShape.medium,
    ) {
        Row(
            modifier = Modifier
                .padding(WeSignDimension.PaddingLarge)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(image),
                    contentDescription = "Chat Support Icon",
                    modifier = Modifier.size(36.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = title,
                        style = Typography.titleMedium.copy(fontFamily = FontFamily(Font(R.font.inter_bold))),
                        color = Color(0xFF333333)
                    )
                    Text(
                        text = content,
                        style = Typography.titleSmall.copy(fontFamily = FontFamily(Font(R.font.inter_regular))),
                        color = Color(0xFF666666)
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Forward Arrow",
                tint = Color(0xFF333333)
            )
        }
    }
}


data class ItemSlide(
    val title: String,
    val content: String,
    val icon: ImageVector,
    val iconColor: Color,
    val gradient: List<Color>,

    )