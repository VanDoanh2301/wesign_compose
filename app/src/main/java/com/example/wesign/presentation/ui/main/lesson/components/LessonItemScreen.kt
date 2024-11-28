package com.example.wesign.presentation.ui.main.lesson.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.wesign.R
import com.example.wesign.domain.model.Part
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.gradient2
import com.example.wesign.presentation.theme.gradient5
import com.example.wesign.presentation.ui.main.lesson.LessonPartState
import com.example.wesign.utils.showToast

@Composable
fun LessonItemScreen(
    modifier: Modifier = Modifier,
    position: Int = 0,
    onLessonClick: (Part) -> Unit = {},
    lessonAPart: LessonPartState
) {
    val context = LocalContext.current
    var isShowPart by remember { mutableStateOf(false) }
    val lesson = lessonAPart.lesson
    val listPart = lessonAPart.part
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .border(
                    border = BorderStroke(
                        1.dp,
                        color = if (position % 2 == 0) Color(0xFF16222A) else Color(0xFF3a7bd5)
                    ),
                    shape = WeSignShape.medium,
                )
                .background(
                    brush = Brush.linearGradient(
                        colors = if (position % 2 == 0) gradient2 else gradient5,
                    ), shape = WeSignShape.medium
                )
                .clip(WeSignShape.medium)
                .clickable {

                    if (listPart.isNotEmpty()) {
                        isShowPart = !isShowPart
                    } else {
                        context.showToast("Không có phần nào !")
                    }



                }
        ) {
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                if (lesson.imageLocation != null) {
                    AsyncImage(
                        model = lesson.imageLocation,
                        contentDescription = "",
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .shadow(elevation = 4.dp),
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.teacher),
                        contentDescription = "",
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .shadow(elevation = 4.dp),
                        contentScale = ContentScale.Crop,
                    )
                }


                Text(
                    text = lesson.lessonName,
                    style = Typography.titleMedium.copy(Color.White),
                    modifier = Modifier.padding(start = 10.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = {}) {
                    Image(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            }

        }


        AnimatedVisibility(visible = isShowPart) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .background(color = Color.Gray.copy(alpha = 0.2f), shape = WeSignShape.medium)
            ) {

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(3),
                ) {
                    items(listPart.size) { partIndex ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp)
                                .padding(4.dp)
                                .background(color = Color.White.copy(0.5f), shape = WeSignShape.medium)
                                .clip(WeSignShape.medium)
                                .clickable {
                                    onLessonClick(listPart[partIndex])
                                }
                            ,
                            contentAlignment = androidx.compose.ui.Alignment.CenterStart

                        ) {
                            Row(
                                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                                modifier = Modifier.padding(start = 8.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Color.White)
                                        .align(Alignment.CenterVertically),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${partIndex + 1}",
                                        style = Typography.titleSmall,
                                    )
                                }
                                Text(
                                    text = listPart.get(partIndex).partName,
                                    style = Typography.titleSmall.copy(fontSize = 14.sp),
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                    }
                }

            }
        }



    }

}