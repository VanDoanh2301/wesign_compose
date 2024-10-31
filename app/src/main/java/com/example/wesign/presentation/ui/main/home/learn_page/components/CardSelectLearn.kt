package com.example.wesign.presentation.ui.main.home.learn_page.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.theme.BlueStart
import com.example.wesign.presentation.theme.GreenStart
import com.example.wesign.presentation.theme.OrangeStart
import com.example.wesign.presentation.theme.PurpleStart
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
@Preview(showBackground = true)
fun CardSelectLearn(
    bottomBarHeight: Dp = 68.dp, animationSpec: AnimationSpec<Dp> =
        spring(
            dampingRatio = 1f,
            stiffness = Spring.StiffnessMediumLow,
        )
) {
    val items = listOf(
        LearnItem("Classroom", R.drawable.ic_classroom, PurpleStart),
        LearnItem("Topic", R.drawable.ic_topic, BlueStart),
        LearnItem("Vocabulary", R.drawable.ic_vocabulary,OrangeStart ),
        LearnItem("Number", R.drawable.ic_number, GreenStart),
        LearnItem("Alphabet", R.drawable.ic_alphabet, GreenStart),
    )
    var selectedItem by remember { mutableIntStateOf(0) }
    BoxWithConstraints(
        modifier = Modifier
            .background(color = Color.White)
            .padding(horizontal = Dp(bottomBarHeight / (bottomBarHeight / 16)))
    ) {
        val maxWidth = this.maxWidth
        val indicatorOffset: Dp by animateDpAsState(
            targetValue =
            (maxWidth / (items.size.takeIf { it != 0 } ?: 1)) * selectedItem,
            animationSpec = animationSpec,
            label = "indicator",
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(1f / ( items.size.takeIf { it != 0 } ?: 1))
                .offset(x = indicatorOffset),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(1 - (1f / ( items.size.takeIf { it != 0 } ?: 1)))
                    .height(bottomBarHeight)
                    .background(color = primaryLight)
            )
        }

        LazyRow {
            items(items.size) { index ->
                CardSelectItem(item = items[index], onSelected = {
                    selectedItem = index
                })
            }
        }

    }

}

@Composable
fun CardSelectItem(item: LearnItem,onSelected: (LearnItem) -> Unit) {
    Row(
        modifier = Modifier
            .padding(end= WeSignDimension.PaddingMedium)
            .fillMaxWidth()
            .height(48.dp)
            .background(color = Color.White, shape = WeSignShape.large)
            .padding(start = WeSignDimension.PaddingMedium)
            .clickable {
                onSelected(item)
            }
        ,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(36.dp).background(color = item.color, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.padding(WeSignDimension.PaddingMedium))
        Text(
            text = item.title,
            style = Typography.titleSmall,
        )
    }
}

data class LearnItem(
    val title: String,
    val icon: Int,
    val color: Color
)