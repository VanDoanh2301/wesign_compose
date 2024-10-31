package com.example.wesign.presentation.ui.main.home.home_page.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wesign.R
import com.example.wesign.presentation.theme.BlueEnd
import com.example.wesign.presentation.theme.BlueStart
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight



@Composable
@Preview(showSystemUi = true)
fun RecommendedCoursesRow(
    modifier: Modifier = Modifier,
    title: String = "Recommended for you",
    isClassed: Boolean = false
) {
    val courseTopics = listOf(
        CourseTopic(
            "Biology for class XIII",
            "Smith J.",
            17,
            "40 Mins",
            R.drawable.teacher,
            Color(0xFF2196F3)
        ),
        CourseTopic(
            "Math for class XIII",
            "Smith J.",
            17,
            "40 Mins",
            R.drawable.teacher,
            Color(0xFFFFA726)
        )
    )

    Column(modifier = Modifier) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = Typography.headlineSmall.copy(fontFamily = FontFamily(Font(R.font.inter_medium))),
            )
            Text(
                text = "See all",
                style = Typography.titleMedium.copy(color = BlueStart),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth().heightIn(260.dp, 280.dp).wrapContentSize(unbounded = false)
        ) {
            items(courseTopics.size) { course ->
                CourseTopic(course = courseTopics[course], isClassed)
            }
        }
    }
}


@Composable
fun CourseTopic(course: CourseTopic, isClassed: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(end = WeSignDimension.PaddingLarge)
        ,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = WeSignShape.medium
    ) {
        Column(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .widthIn(160.dp, 180.dp)
                    .height(160.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White,
                                course.overlayColor.copy(alpha = 0.2f),
                                course.overlayColor,

                                )
                        ), shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)

                    ), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = course.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.4f),
                )
                Icon(
                    imageVector = Icons.Default.PlayCircle,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center)
                )

            }
            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            Column(
                modifier = Modifier.padding(
                    start = WeSignDimension.PaddingLarge,
                    end = WeSignDimension.PaddingLarge,
                    bottom = WeSignDimension.PaddingLarge
                ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = course.title,
                    style = Typography.titleSmall.copy(fontFamily = FontFamily(Font(R.font.inter_medium)))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "By ${course.author}",
                    style = Typography.labelSmall.copy(color = Color.Gray)
                )
                Spacer(modifier = Modifier.height(4.dp))

                if (!isClassed) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = "Files",
                            tint = primaryLight,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${course.filesCount} Files",
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "Duration",
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = course.duration,
                            fontSize = 12.sp
                        )
                    }
                }

            }
        }
    }
}

data class CourseTopic(
    val title: String,
    val author: String,
    val filesCount: Int,
    val duration: String,
    val imageRes: Int,
    val overlayColor: Color
)






















