package com.example.wesign.presentation.ui.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.SecurityUpdateGood
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.component.CustomLoading
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.presentation.ui.auth.register.REGEX_EMAIL
import com.example.wesign.presentation.ui.auth.success.LottieAnimationCustom
import com.example.wesign.utils.showToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// String constants
const val LOGIN_TEXT = "Log in"
const val EMAIL_LABEL = "Email"
const val PASSWORD_LABEL = "Password"
const val OR_TEXT = "OR"
const val GOOGLE_LOGIN_TEXT = "Login without account"
const val NO_ACCOUNT_TEXT = "Don't have any account?"
const val REGISTER_TEXT = "Register"

//@Composable
//@Preview(showBackground = true)
//fun LoginScreenPreview() {
//    LoginScreen()
//}

@Composable
fun LoginScreen(
    state: LoginScreenState,
    event: (LoginScreenEvent) -> Unit = {},
    onRegisterClick: () -> Unit = {}, onLoginClick: () -> Unit = {}
) {
    var textEmail by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }
    var isLogin by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(16.dp)
                .imePadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_login), // Replace with your image resource
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            // Login Text
            Text(
                text = LOGIN_TEXT,
                style = Typography.headlineSmall.copy(
                    color = primaryLight,
                    fontFamily = FontFamily(Font(R.font.inter_bold))
                ),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            // Email Input Field
            OutlinedTextField(
                value = textEmail,
                onValueChange = { textEmail = it },
                label = {
                    Text(
                        text = EMAIL_LABEL,
                        style = Typography.titleSmall.copy(color = Color.Gray)
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null,
                        tint = primaryLight
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            // Password Input Field
            OutlinedTextField(
                value = textPassword,
                onValueChange = { textPassword = it },
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(
                        text = PASSWORD_LABEL,
                        style = Typography.titleSmall.copy(color = Color.Gray)
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Password,
                        contentDescription = null,
                        tint = primaryLight
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            // Login Button
            Button(
                onClick = {
                    scope.launch {
                        val emailPattern = Regex(REGEX_EMAIL)
                        if (textEmail.isEmpty() || textPassword.isEmpty()) {
                            context.showToast("Please fill all fields")
                            return@launch

                        }
                        if (!emailPattern.matches(textEmail)) {
                            context.showToast("Email không hợp lệ")
                            return@launch
                        }

                        event(LoginScreenEvent.onLogin(textEmail, textPassword))
                        isLogin = true
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryLight,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = WeSignShape.small
            ) {
                Text(text = LOGIN_TEXT, style = Typography.titleSmall)
            }

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            // OR Text
            Text(text = OR_TEXT, style = Typography.titleSmall.copy(color = Color.Gray))

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            // Google Login Button
            OutlinedButton(
                onClick = { /* handle google login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                border = BorderStroke(1.dp, Color.Transparent),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Gray.copy(alpha = 0.2f)
                ),
                shape = WeSignShape.small
            ) {
                Icon(
                    Icons.Default.AssignmentInd, // Replace with Google icon resource
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = primaryLight
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = GOOGLE_LOGIN_TEXT,
                    style = Typography.titleSmall.copy(color = primaryLight)
                )
            }

            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            Row {
                Text(text = NO_ACCOUNT_TEXT, style = Typography.titleSmall.copy(color = Color.Gray))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = REGISTER_TEXT,
                    style = Typography.titleSmall.copy(
                        color = Color(0xFF55a3ff),
                        fontFamily = FontFamily(Font(R.font.inter_regular))
                    ),
                    modifier = Modifier.clickable {
                        onRegisterClick()
                    }
                )
            }
        }

        if (isLogin) {
           when {
               state.isLoading -> {
                   CustomLoading()
               }
               state.isSuccessful -> {
                   isLogin = false
                   onLoginClick()
               }
               state.error != null -> {
                   isLogin = false
                   LocalContext.current.showToast("Account or password is incorrect")
               }
           }
        }

    }

}

