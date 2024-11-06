package com.example.wesign.presentation.ui.main.home.home_page.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.wesign.R
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.presentation.component.CustomLoading
import com.example.wesign.presentation.theme.BlueEnd
import com.example.wesign.presentation.theme.BlueStart
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight


@Composable
fun RecommendedCoursesRow(
    modifier: Modifier = Modifier,
    title: String = "Recommended for you",
    classrooms: LazyPagingItems<ClassRoom>, ) {
    Column(modifier = modifier) {
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
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(260.dp, 280.dp)
                .wrapContentSize(unbounded = false)
        ) {
//            item {
//                if (classrooms.loadState.refresh is LoadState.Loading) {
//                    CustomLoading()
//                }
//            }
            items(count = classrooms.itemCount) { index ->
                val item = classrooms[index]
                CourseTopiScreen(
                    course = item!!,
                )
        }

    }
}
}


@Composable
fun CourseTopiScreen(course: ClassRoom, onClickItem: () -> Unit = {}) {
    val randomColor = listOf(
        Color(0xFFFFA726),
        Color(0xFF2196F3)
    ).shuffled().first()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(end = WeSignDimension.PaddingLarge, bottom = WeSignDimension.PaddingLarge)
            .border(2.dp, randomColor, shape = WeSignShape.medium),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = WeSignShape.medium,
        onClick = { onClickItem() }
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
                                randomColor.copy(alpha = 0.2f),
                                randomColor,

                                )
                        ), shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)

                    ), contentAlignment = Alignment.Center
            ) {
                if (course.imageLocation != null) {
                    AsyncImage(
                        model = course.imageLocation,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.4f)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.teacher),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.4f),
                    )
                }

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
                    text = course.content,
                    style = Typography.titleSmall.copy(fontFamily = FontFamily(Font(R.font.inter_medium)))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tạo bởi ${course.createdBy}",
                    style = Typography.labelSmall.copy(color = Color.Gray)
                )
                Spacer(modifier = Modifier.height(4.dp))
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
                        text = "Files",
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }

            }
        }
    }
}
























