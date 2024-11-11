package com.example.wesign.presentation.ui.main.result

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.ui.main.result.components.BOX_BORDER_WIDTH
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.presentation.ui.auth.login.LOGIN_TEXT
import com.example.wesign.presentation.ui.auth.login.LoginScreenEvent
import com.example.wesign.presentation.ui.auth.register.REGEX_EMAIL
import com.example.wesign.presentation.ui.main.result.components.CustomResultBody
import com.example.wesign.utils.showToast
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultTestScreen(
    countCorrect: Int = 0,
    totalQuestions: Int = 0,
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text =  "Kết quả",
                        style = Typography.titleLarge.copy(fontFamily = FontFamily(Font(R.font.inter_bold)))
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {onBack.invoke() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                CustomResultBody(
                    countCorrect,
                    totalQuestions
                )
            }
        },
        bottomBar = {
            Button(
                onClick = {
                    onBack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryLight,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = WeSignDimension.PaddingMedium, vertical = WeSignDimension.PaddingMedium)
                    .height(48.dp),
                shape = WeSignShape.small
            ) {
                Text(text = "Quay lại", style = Typography.titleSmall)
            }

        }

    )
}