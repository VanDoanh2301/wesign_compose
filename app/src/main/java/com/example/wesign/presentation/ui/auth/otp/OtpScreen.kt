package com.example.wesign.presentation.ui.auth.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.component.OtpInputField
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.presentation.ui.auth.register.RegisterScreenEvent

//String constants
const val ENTER_OTP_TEXT = "Enter OTP"
const val TITLE_TEXT =
    "5 digit code sent to your email please check and confirm the code to continue"
const val SUBMIT_TEXT = "Submit"
const val RESEND_TEXT = "Didn't receive the code? Resend in"

@Composable

fun OtpScreen(
    onSuccessClick: () -> Unit = {},
    state: OtpScreenState,
    event: (OtpScreenEvent) -> Unit,
    eventRegister: (RegisterScreenEvent) -> Unit
) {
    val otpValue = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        event(OtpScreenEvent.resendOtp)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())

    ) {
        IconButton(onClick = { }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.img_register), // Replace with your image resource
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            Text(
                text = ENTER_OTP_TEXT,
                style = Typography.headlineSmall.copy(
                    color = primaryLight,
                    fontFamily = FontFamily(Font(R.font.inter_bold))
                ),
            )
            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            Text(
                text = TITLE_TEXT,
                style = Typography.titleSmall.copy(fontFamily = FontFamily(Font(R.font.inter_regular))),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            OtpInputField(
                otp = otpValue,
                count = 5,
                otpBoxModifier = Modifier
                    .size(48.dp)
                    .background(Color.Gray.copy(alpha = 0.1f), WeSignShape.small)

            )

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            Button(
                onClick = { onSuccessClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryLight,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = WeSignShape.small
            ) {
                Text(text = SUBMIT_TEXT, style = Typography.titleSmall)
            }

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            Row {
                Text(text = RESEND_TEXT, style = Typography.titleSmall.copy(color = Color.Gray))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = state.time,
                    style = Typography.titleSmall.copy(
                        color = Color(0xFF55a3ff),
                        fontFamily = FontFamily(Font(R.font.inter_regular))
                    ),
                    modifier = Modifier.clickable {
                        if (state.isResend) {
                            event(OtpScreenEvent.resendOtp)
                            eventRegister(RegisterScreenEvent.generateOtp("nam","nam@gmail.com","dsd" , "USER"))
                        }
                    }
                )
            }
        }

    }
}