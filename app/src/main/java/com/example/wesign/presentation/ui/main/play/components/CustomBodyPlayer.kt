package com.example.wesign.presentation.ui.main.play.components

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.wesign.R
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.model.WordType
import com.example.wesign.presentation.component.ShimmerListItem
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseVocabularyScreen
import com.example.wesign.presentation.ui.main.home.home_page.components.RecommendedVocabularyRow
import com.example.wesign.utils.generateRandomColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CustomBodyPlayer(
    pagerState: PagerState,
    scope: CoroutineScope,
    vocabulary: Vocabulary,
) {
    val activity = LocalContext.current as Activity
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WeSignDimension.PaddingLarge),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.4f)
                    ) {
                        Text(
                            text = when (vocabulary.type) {
                                WordType.WORD -> "Từ vựng: " + vocabulary.content
                                WordType.SENTENCE -> "Câu: " + vocabulary.content
                                WordType.PARAGRAPH -> "Đoạn văn: " + vocabulary.content
                            },
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily(
                                    Font(R.font.inter_bold)
                                )
                            ),
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                        )

                        Text(
                            text = "Chủ đề: " + vocabulary.topicContent,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            scope.launch {
                                if (pagerState.currentPage > 0) {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }

                            }
                        }) {
                            Icon(
                                Icons.Filled.SkipPrevious,
                                contentDescription = "Play",
                                Modifier.size(32.dp)
                            )
                        }

                        IconButton(onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }) {
                            Icon(
                                Icons.Filled.SkipNext,
                                contentDescription = "Play",
                                Modifier.size(32.dp)
                            )
                        }

                        IconButton(onClick = {

                        }) {
                            Icon(Icons.Filled.Share, contentDescription = "Play")
                        }
                    }


                }
            }



            if (vocabulary.images!!.isNotEmpty() && vocabulary.images.first().location != null) {

                Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))

                Text(
                    text = "Ảnh mô tả",
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily(Font(R.font.inter_bold))),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = WeSignDimension.PaddingLarge)
                )
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingMedium))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(18f / 9f), contentAlignment = Alignment.Center
                ) {
                    LazyRow(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(vocabulary.images.size) {
                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = WeSignDimension.PaddingLarge)
                                    .border(2f.dp, generateRandomColor(), WeSignShape.medium)
                            ) {
                                AsyncImage(
                                    model = vocabulary.images[it].location,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        , contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(R.drawable.img_empty_data), contentDescription = "")
                }
            }

        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                activity.finish()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryLight,
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(WeSignDimension.PaddingLarge)
                .fillMaxWidth()
                .height(48.dp),
            shape = WeSignShape.small
        ) {
            Text(text = "Quay lại", style = Typography.titleSmall)
        }
    }
}

//partyrichy@dodoberat.com