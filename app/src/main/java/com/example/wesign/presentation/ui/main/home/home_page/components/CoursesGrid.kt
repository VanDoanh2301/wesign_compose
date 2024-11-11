package com.example.wesign.presentation.ui.main.home.home_page.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wesign.R
import com.example.wesign.presentation.theme.BlueStart
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape

@Composable

fun CoursesGrid(onClickNext: (Int) -> Unit) {
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Học tập",
                style = Typography.headlineSmall.copy(fontFamily = FontFamily(Font(R.font.inter_medium))),
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth().heightIn(80.dp, 140.dp) .wrapContentSize(unbounded = false),
            columns = GridCells.Fixed(2),

        ) {
            items(courses.size) { course ->
                CourseItem(course = courses[course],course) {
                    onClickNext(course)
                }
            }
        }
    }
}


@Composable
fun CourseItem(course: Course, index: Int, onCourseClick: (Course) -> Unit = {}) {
    val spacerStart = if (index % 2 != 0) WeSignDimension.PaddingMedium else 0.dp
    val spacerEnd = if (index % 2 == 0) WeSignDimension.PaddingMedium else 0.dp

    Row(
        modifier = Modifier
            .padding(bottom = WeSignDimension.PaddingLarge, start = spacerStart , end = spacerEnd)
            .fillMaxWidth()
            .height(48.dp)
            .background(color = Color.White, shape = WeSignShape.large)
            .clip(shape = WeSignShape.large).clickable {
                onCourseClick(course)
            }
            .padding(start = WeSignDimension.PaddingMedium)
        ,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(36.dp).background(color = course.backgroundColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = course.icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.padding(WeSignDimension.PaddingMedium))
        Text(
            text = course.title,
            style = Typography.titleSmall,
        )
    }
}


val courses = listOf(
    Course("Chủ đề", R.drawable.trending_topic, Color(0xFF6A5ACD)),
    Course("Lớp học", R.drawable.research, Color(0xFFFFA726)),
    Course("Kiểm tra", R.drawable.checklist, Color(0xFF66BB6A)),
    Course("Thực hành", R.drawable.hello, Color(0xFFFFCA28))
)

data class Course(
    val title: String,
    val icon: Int,
    val backgroundColor: Color
)