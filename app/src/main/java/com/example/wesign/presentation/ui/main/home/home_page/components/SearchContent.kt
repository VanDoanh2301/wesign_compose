package com.example.wesign.presentation.ui.main.home.home_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.primaryLight

@Composable
fun SearchContent() {
    var textSearch by remember {
        mutableStateOf("Search...")
    }
    Row {
        Box(
            modifier = Modifier.height(56.dp).background(
                color = primaryLight,
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    bottomStart = 8.dp
                )
            ), contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = WeSignDimension.PaddingLarge)) {
                Text(
                    text = "All Category",
                    style = Typography.labelMedium.copy(color = Color.White),)
                Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "", tint = Color.White)
            }

        }

        Box(
            modifier = Modifier.height(56.dp).weight(1f).background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topEnd = 8.dp,
                    bottomEnd = 8.dp
                )
            )
        ) {
            SearchOnBoarding() {
                textSearch = it
            }
        }

    }
}