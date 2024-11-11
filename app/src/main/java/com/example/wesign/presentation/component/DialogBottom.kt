package com.example.wesign.presentation.component

import android.view.Gravity
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight


const val DIALOG_HEIGHT = 195

@Composable
fun DialogBottom(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    title: String,
) {
    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = WeSignDimension.PaddingLarge)) {
        val marginInPixels = 32.dp.dpToPx().toInt()
        Dialog(onDismissRequest = { onDismissRequest() }) {
            (LocalView.current.parent as DialogWindowProvider).apply {
                window.setGravity(Gravity.CENTER)
                window.setLayout(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DIALOG_HEIGHT.dp),
                shape = WeSignShape.medium,
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        text = title,
                        modifier = Modifier.padding(WeSignDimension.PaddingMedium),
                        style = Typography.titleLarge.copy(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = { onDismissRequest() },
                            modifier = Modifier
                                .padding(WeSignDimension.PaddingMedium)
                                .weight(1f)
                                .background(
                                    color = primaryLight, shape = WeSignShape.medium
                                ),
                        ) {
                            Text(
                                text = "Hủy",
                                color = Color.White,
                                style = Typography.titleMedium
                            )
                        }
                        TextButton(
                            onClick = { onConfirmation() },
                            modifier = Modifier
                                .padding(WeSignDimension.PaddingMedium)
                                .weight(1f)
                                .background(
                                    color = primaryLight, shape = WeSignShape.medium
                                ),
                        ) {
                            Text(
                                text = "Đồng ý",
                                color = Color.White,
                                style = Typography.titleMedium

                            )
                        }
                    }
                }
            }
        }
    }

}