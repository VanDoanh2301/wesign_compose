package com.example.wesign.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val WeSignShape = Shapes(
    small = RoundedCornerShape(size = 8.dp),
    medium = RoundedCornerShape(size = 12.dp),
    large = RoundedCornerShape(size = 16.dp),
)

object WeSignDimension {
    val PaddingSmall = 4.dp
    val PaddingMedium = 8.dp
    val PaddingLarge = 16.dp
    val PaddingExtraLarge = 24.dp
}
