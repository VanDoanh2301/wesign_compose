package com.example.wesign.presentation.ui.main.exam

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.domain.model.Exam
import com.example.wesign.presentation.component.shimmerEffect
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.gradient4
import com.example.wesign.presentation.ui.main.test.components.CustomTestTopBar
import com.example.wesign.utils.generateRandomColor

@Composable
fun ExamScreen(
    state: ExamScreenState,
    event: (ExamScreenEvent) -> Unit,
    onClickExam:(Int) -> Unit = {},
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        event(ExamScreenEvent.GetAllExam)
    }



    Scaffold(
        modifier = Modifier.fillMaxSize().paint(painterResource(R.drawable.bg_home_1), contentScale = ContentScale.Crop),
        containerColor = Color.Transparent,
        topBar = {
            Box(modifier = Modifier.fillMaxWidth().height(56.dp).background(Color.White)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = {onBackClick()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.width(WeSignDimension.PaddingLarge))
                    Text(text = "Danh sách bài kiểm tra", style = Typography.titleLarge.copy(fontFamily = FontFamily(Font(R.font.inter_bold))))
                }

            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            when {
                state.isLoading -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(WeSignDimension.PaddingMedium),
                    ) {
                        items(5) { exam ->
                            Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = WeSignDimension.PaddingLarge)
                                    .fillMaxWidth()
                                    .background(
                                       color = Color.White, shape = WeSignShape.medium
                                    )
                            ) {
                                ListItem(
                                    headlineContent = {
                                        Box(modifier = Modifier.shimmerEffect())
                                    },
                                    supportingContent = {
                                        Box(modifier = Modifier.shimmerEffect())
                                    },
                                    leadingContent = {
                                        Image(
                                            painter = painterResource(R.drawable.ic_topic),
                                            contentDescription = null,
                                            Modifier.size(32.dp)
                                        )
                                    },
                                    colors = ListItemDefaults.colors(
                                        containerColor = Color.Transparent
                                    ),
                                    trailingContent = {
                                        Box(modifier = Modifier.shimmerEffect())
                                    },
                                )
                            }
                            Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))

                        }
                    }
                }
                state.listExams.isNotEmpty() -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(WeSignDimension.PaddingMedium),
                    ) {
                        items(state.listExams.size) { exam ->
                            Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = WeSignDimension.PaddingLarge)
                                    .fillMaxWidth()
                                    .background(
                                        brush = Brush.linearGradient(
                                            gradient4
                                        ), shape = WeSignShape.medium
                                    )
                            ) {
                                ListItem(
                                    headlineContent = {
                                        Text(
                                            text = state.listExams[exam].name,
                                            style = Typography.titleMedium.copy(color = Color.White)
                                        )
                                    },
                                    supportingContent = {
                                        Text(
                                            text = "Number of Questions: ${state.listExams[exam].numberOfQuestions}",
                                            style = Typography.titleSmall.copy(
                                                color = Color.Black, fontFamily = FontFamily(
                                                    Font(R.font.inter_regular)
                                                )
                                            )
                                        )
                                    },
                                    leadingContent = {
                                        Image(
                                            painter = painterResource(R.drawable.ic_topic),
                                            contentDescription = null,
                                            Modifier.size(32.dp)
                                        )
                                    },
                                    colors = ListItemDefaults.colors(
                                        containerColor = Color.Transparent
                                    ),
                                    trailingContent = {
                                        Icon(
                                            Icons.Default.ArrowRight,
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(shape = WeSignShape.medium)
                                        .clickable {
                                            onClickExam(state.listExams[exam].classRoomId)
                                        },
                                )
                            }
                            Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))

                        }
                    }
                }
            }

        }

    }


}

