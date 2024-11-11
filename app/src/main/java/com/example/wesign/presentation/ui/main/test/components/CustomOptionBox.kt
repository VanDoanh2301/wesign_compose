package com.example.wesign.presentation.ui.main.test.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wesign.presentation.theme.ErrorColor
import com.example.wesign.presentation.theme.SuccessColor
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomOptionBox(
    option: String,
    answer: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    onClick: () -> Unit = {},
    answerCorrect: String,
    selectedOption: String?
) {
    var scale by remember { mutableFloatStateOf(1f) }
    val backgroundColor = when {
        isSelected && isCorrect -> SuccessColor
        isSelected && !isCorrect -> ErrorColor
        selectedOption != null && answerCorrect == answer -> SuccessColor
        else -> Color.White
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(shape = WeSignShape.medium)
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        scale = 0.9f
                        tryAwaitRelease()
                        scale = 1f
                    },
                    onTap = {
                        onClick()
                        Log.d("CustomOptionBox", "Option selected: $option")
                    }
                )
            }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = option,
                color = if (isSelected|| selectedOption != null && answerCorrect == answer) Color.White else Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .background(
                        backgroundColor,
                        shape = WeSignShape.medium
                    )
                    .padding(WeSignDimension.PaddingLarge)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = answer,
                color = if (isSelected || selectedOption != null && answerCorrect == answer) Color.White else Color.Black,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        backgroundColor,
                        shape = WeSignShape.medium
                    )
                    .padding(WeSignDimension.PaddingLarge)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }
}