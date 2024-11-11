package com.example.wesign.presentation.ui.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.component.CustomLoading
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.utils.showToast
import kotlin.reflect.KFunction1

// Define constants for strings
private const val LABEL_FULL_NAME = "Họ và tên"
private const val LABEL_EMAIL = "Email"
private const val LABEL_PASSWORD = "Mật khẩu"
private const val SIGN_UP_TEXT = "Đăng ký"
private const val REGISTRATION_TEXT = "Đăng ký tài khoản"
private const val ALREADY_HAVE_ACCOUNT_TEXT = "Đã có tài khoản?"
private const val LOGIN_TEXT = "Đăng nhập"
private const val TERMS_CONDITIONS_TEXT = "Điều khoản & Điều kiện"
private const val PRIVACY_POLICY_TEXT = "Chính sách bảo mật"
private const val AGREEMENT_TEXT = "Bằng cách đăng ký, bạn đồng ý với "
const val ERROR_EMAIL = "Email không hợp lệ"
const val PLEASE_FILL_ALL_FIELD = "Vui lòng điền đầy đủ thông tin"
const val REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"

@Composable
fun RegisterScreen(
    onOtpClick: (String, String, String) -> Unit,
    state: RegisterScreenState,
    event: (RegisterScreenEvent) -> Unit
) {
    var textEmail by remember { mutableStateOf(state.email) }
    var textPassword by remember { mutableStateOf(state.password) }
    var textName by remember { mutableStateOf(state.name) }
    var isShowLoading by remember { mutableStateOf(false) }
    var isRegisterClick by remember { mutableStateOf(false) }
    val context = LocalContext.current
    // Define text field labels and icons
    val inputFields = listOf(
        InputField(
            LABEL_FULL_NAME,
            Icons.Default.Person,
            { textName },
            { newValue -> textName = newValue }),
        InputField(
            LABEL_EMAIL,
            Icons.Default.Email,
            { textEmail },
            { newValue -> textEmail = newValue }),
        InputField(
            LABEL_PASSWORD,
            Icons.Default.Password,
            { textPassword },
            { newValue -> textPassword = newValue })
    )

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray)) { append(AGREEMENT_TEXT) }
        pushStringAnnotation(tag = "TERMS", annotation = "terms_and_conditions")
        withStyle(
            style = SpanStyle(
                color = Color(0xFF55a3ff),
                fontFamily = FontFamily(Font(R.font.inter_regular))
            )
        ) {
            append(TERMS_CONDITIONS_TEXT)
        }
        pop()
        withStyle(style = SpanStyle(color = Color.Gray)) { append(" and ") }
        pushStringAnnotation(tag = "PRIVACY", annotation = "privacy_policy")
        withStyle(
            style = SpanStyle(
                color = Color(0xFF55a3ff),
                fontFamily = FontFamily(Font(R.font.inter_regular))
            )
        ) {
            append(PRIVACY_POLICY_TEXT)
        }
        pop()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isRegisterClick) {
            when {
                state.isLoading -> {
                    isShowLoading = true
                }

                state.isOtpVerified -> {
                    isShowLoading = false
                    isRegisterClick = false
                    onOtpClick(textEmail, textName, textPassword)
                }

                state.error != null -> {
                    isShowLoading = false
                    isRegisterClick = false
                    context.showToast(state.error)
                }
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(
                    rememberScrollState()
                )

        ) {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "")
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_otp), // Replace with your image resource
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

                Text(
                    text = SIGN_UP_TEXT,
                    style = Typography.headlineSmall.copy(
                        color = primaryLight,
                        fontFamily = FontFamily(Font(R.font.inter_bold))
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

                Text(
                    text = annotatedString,
                    style = Typography.titleSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Handle annotation clicks */ }
                )

                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

                // Generate OutlinedTextFields dynamically
                inputFields.forEach { (label, icon, valueGetter, valueSetter) ->
                    OutlinedTextField(
                        value = valueGetter(),
                        onValueChange = { newText -> valueSetter(newText) },
                        label = {
                            Text(
                                label,
                                style = Typography.titleSmall.copy(color = Color.Gray)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                icon,
                                contentDescription = null,
                                tint = primaryLight
                            )
                        },
                        visualTransformation =if (label == LABEL_PASSWORD)  PasswordVisualTransformation() else VisualTransformation.None,
                        keyboardOptions = if (label == LABEL_PASSWORD) {
                            KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            )
                        } else KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),


                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = WeSignDimension.PaddingSmall)
                    )
                }

                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

                Button(
                    onClick = {
                        val emailPattern = Regex(REGEX_EMAIL)
                        if (textEmail.isNotEmpty() && textPassword.isNotEmpty() && textName.isNotEmpty()) {
                            if (emailPattern.matches(textEmail)) {
                                isRegisterClick = true
                                event(
                                    RegisterScreenEvent.generateOtp(
                                        textName,
                                        textEmail,
                                        textPassword,
                                        "USER"
                                    )
                                )
                            } else {
                                context.showToast(ERROR_EMAIL)
                            }
                        } else {
                            context.showToast(PLEASE_FILL_ALL_FIELD)
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
                    Text(text = REGISTRATION_TEXT, style = Typography.titleSmall)
                }

                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

                Row {
                    Text(
                        text = ALREADY_HAVE_ACCOUNT_TEXT,
                        style = Typography.titleSmall.copy(color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = LOGIN_TEXT,
                        style = Typography.titleSmall.copy(
                            color = Color(0xFF55a3ff),
                            fontFamily = FontFamily(Font(R.font.inter_regular))
                        ),
                        modifier = Modifier.clickable { /* handle login navigation */ }
                    )
                }
            }
        }

        if (isShowLoading) {
          CustomLoading()
        }

    }
}

data class InputField(
    val label: String,
    val icon: ImageVector,
    val valueGetter: () -> String,
    val valueSetter: (String) -> Unit
)


