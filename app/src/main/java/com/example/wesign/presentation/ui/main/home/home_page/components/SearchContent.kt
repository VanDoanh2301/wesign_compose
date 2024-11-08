package com.example.wesign.presentation.ui.main.home.home_page.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.primaryLight


enum class TypeSearch(val title: String) {
    CLASSROOM("Lớp học"), VOCABULARY("Từ vựng"), TOPIC("Chủ đề")
}

@Composable
fun SearchContent(onSearch: (String, String) -> Unit, addTypeChange: (String) -> Unit = {}) {
    var typeSearch by rememberSaveable {
        mutableStateOf(TypeSearch.CLASSROOM.title)
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var textSearch by remember {
        mutableStateOf("")
    }

   addTypeChange(typeSearch)

    Row {
        Column {
            Box(
                modifier = Modifier
                    .height(56.dp)
                    .background(
                        color = primaryLight,
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            bottomStart = 8.dp
                        )
                    )
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            bottomStart = 8.dp
                        )
                    )
                    .clickable {
                        isExpanded = !isExpanded
                    }, contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = WeSignDimension.PaddingLarge)
                ) {
                    Text(
                        text = typeSearch,
                        style = Typography.labelLarge.copy(color = Color.White),
                    )
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                CustomDropDownMenu(expanded = isExpanded, onQuestionSelected = { count ->
                    typeSearch = count
                    isExpanded = false
                }, onDismissExpand = {
                    isExpanded = false
                },
                    modifier = Modifier.background(color = primaryLight)
                )
            }

        }


        Box(
            modifier = Modifier
                .height(56.dp)
                .weight(1f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topEnd = 8.dp,
                        bottomEnd = 8.dp
                    )
                )
        ) {
            SearchOnBoarding(
                onValueChange = {
                textSearch = it
            },
                onSearch = {
                    onSearch(typeSearch, textSearch)
                }
            )
        }

    }
}


@Composable
fun CustomDropDownMenu(
    expanded: Boolean,
    onQuestionSelected: (String) -> Unit,
    onDismissExpand: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val alpha by animateFloatAsState(targetValue = if (expanded) 1f else 0f, label = "")

    if (expanded || alpha > 0f) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDismissExpand() },
            modifier = modifier
                .offset(y = 5.dp),
            offset = DpOffset(-5.dp, 5.dp)
        ) {

            TypeSearch.entries.forEach { count ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = count.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White
                        )
                    },
                    onClick = {
                        onDismissExpand()
                        onQuestionSelected(count.title)
                    }
                )
            }
        }
    }
}