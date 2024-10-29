package com.example.wesign.presentation.ui.onboar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.presentation.theme.WeSignShape


@Composable
fun CustomIndicators(isSelected: Boolean, modifier: Modifier = Modifier) {

    if (isSelected) {
        Box(
            modifier = modifier
                .width(16.dp)
                .height(8.dp)
                .background(color = Color(0xFF5F61F0), WeSignShape.small)


        )
    } else {
        Box(
            modifier = modifier
                .background(color = Color.Gray, shape = CircleShape)
                .size(8.dp)
        )
    }

}