package com.example.wesign.presentation.ui.main.home.home_page.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.wesign.R
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Topic
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.presentation.component.ShimmerListItem
import com.example.wesign.presentation.theme.BlueStart
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.utils.SharedPreferencesUtils
import com.example.wesign.utils.generateRandomColor


@Composable
fun RecommendedClassroomsRow(
    modifier: Modifier = Modifier,
    title: String = "Recommended for you",
    classrooms: LazyPagingItems<ClassRoom>,
) {

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
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(260.dp, 280.dp)
                .wrapContentSize(unbounded = false),
            horizontalArrangement = Arrangement.Absolute.Left
        ) {
            items(count = 3) {
                if (classrooms.loadState.refresh is LoadState.Loading) {
                    ShimmerListItem(isClass = true)
                }
            }
            items(count = classrooms.itemCount) { index ->
                val item = classrooms[index]
                CourseClassScreen(
                    course = item!!,
                )
            }

        }
    }
}


@Composable
fun CourseClassScreen(course: ClassRoom, onClickItem: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(WeSignDimension.PaddingMedium)
            .border(2.dp, course.color, shape = WeSignShape.medium),
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
                                course.color.copy(alpha = 0.2f),
                                course.color,

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
                    style = Typography.titleSmall.copy(
                        fontFamily = FontFamily(Font(R.font.inter_medium))
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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


@Composable
fun RecommendedVocabularyRow(
    modifier: Modifier = Modifier,
    title: String = "Recommended for you",
    vocabularies: LazyPagingItems<Vocabulary>,
) {

    val randomItems = if (SharedPreferencesUtils.getBoolean("first_load", true)) {
        SharedPreferencesUtils.setBoolean("first_load", false)
        vocabularies.itemSnapshotList.shuffled().take(10)
    } else {
        rememberSaveable(vocabularies) {
            vocabularies.itemSnapshotList.shuffled().take(10)
        }
    }


    Column(modifier = modifier) {
        // Header
        if (randomItems.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = Typography.titleLarge.copy(fontFamily = FontFamily(Font(R.font.inter_bold))),
                )
            }
        }


        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(260.dp, 280.dp),
            horizontalArrangement = Arrangement.Absolute.Left

        ) {
            items(count = 3) {
                if (vocabularies.loadState.refresh is LoadState.Loading) {
                    ShimmerListItem()
                }
            }

            items(count = randomItems.size) { index ->
                val item = randomItems[index]
                CourseVocabularyScreen(course = item!!) {

                }

            }

        }
    }
}

val ColorSaver = Saver<Color, Int>(
    save = { color -> color.toArgb() },
    restore = { Color(it) }
)

@Composable
fun CourseVocabularyScreen(course: Vocabulary, onClickItem: () -> Unit) {
    val color = rememberSaveable(saver = ColorSaver) {
        generateRandomColor()
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(WeSignDimension.PaddingMedium)
            .border(2.dp, color, shape = WeSignShape.medium),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = WeSignShape.medium,
        onClick = { onClickItem() }
    ) {
        Column(
            modifier = Modifier.widthIn(160.dp, 180.dp)

        ) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White,
                                color.copy(alpha = 0.2f),
                                color,

                                )
                        ), shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)

                    ), contentAlignment = Alignment.Center
            ) {
                val imageUrl = course.images?.firstOrNull { it.primary }?.location

                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
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
                            .alpha(0.4f)
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
                modifier = Modifier
                    .padding(
                        start = WeSignDimension.PaddingLarge,
                        end = WeSignDimension.PaddingLarge,
                        bottom = WeSignDimension.PaddingLarge
                    )
                    .fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = course.content,
                    style = Typography.titleSmall.copy(
                        fontFamily = FontFamily(Font(R.font.inter_medium))
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tạo bởi IBME lab",
                    style = Typography.labelSmall.copy(color = Color.Gray)
                )
            }
        }
    }
}


@Composable
fun RecommendedTopicRow(
    modifier: Modifier = Modifier,
    title: String = "Recommended for you",
    vocabularies: LazyPagingItems<Vocabulary>,
) {
    val randomItems = vocabularies.itemSnapshotList.shuffled().take(10)

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
            items(count = 3) {
                if (vocabularies.loadState.refresh is LoadState.Loading) {
                    ShimmerListItem()
                }
            }

            items(count = randomItems.size) { index ->
                val item = randomItems[index]
                CourseVocabularyScreen(course = item!!) {

                }

            }

        }
    }
}


@Composable
fun CourseTopicScreen(course: Topic, onClickItem: () -> Unit) {
    Card(
        modifier = Modifier
            .widthIn(160.dp, 180.dp)
            .wrapContentHeight()
            .padding(WeSignDimension.PaddingMedium)
            .border(2.dp, course.color, shape = WeSignShape.medium),
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
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White,
                                course.color.copy(alpha = 0.2f),
                                course.color,

                                )
                        ), shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)

                    ), contentAlignment = Alignment.Center
            ) {
                val imageUrl = course.imageLocation

                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
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
                            .alpha(0.4f)
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
                    style = Typography.titleSmall.copy(
                        fontFamily = FontFamily(Font(R.font.inter_medium))
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tạo bởi IBME lab",
                    style = Typography.labelSmall.copy(color = Color.Gray)
                )
            }
        }
    }
}



