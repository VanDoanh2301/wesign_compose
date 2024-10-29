package com.example.wesign.presentation.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen()
}

@Composable
fun LoginScreen() {
    var textEmail by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
            ,
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
            text = "Log in",
            style = Typography.headlineSmall.copy(color = primaryLight, fontFamily = FontFamily(Font(R.font.inter_bold))),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

        OutlinedTextField(
            value = textEmail,
            onValueChange = { textEmail = it },
            label = { Text(text = "Email", style = Typography.titleSmall.copy(color = Color.Gray)) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = primaryLight) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))


        OutlinedTextField(
            value = textPassword,
            onValueChange = { textPassword = it },
            label = { Text(text = "Password", style = Typography.titleSmall.copy(color = Color.Gray)) },
            leadingIcon = { Icon(Icons.Default.Password , contentDescription = null, tint = primaryLight) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

        // Login Button
        Button(
            onClick = { /* handle login */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryLight,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = WeSignShape.small
        ) {
            Text(text = "Login", style = Typography.titleSmall)
        }

        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

        // OR Text
        Text(text = "OR", style = Typography.titleSmall.copy(color = Color.Gray))

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
            Text(text = "Login without account", style = Typography.titleSmall.copy(color = primaryLight))
        }

        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

        // Register Text
        Row {
            Text(text = "Don't have any account?", style = Typography.titleSmall.copy(color = Color.Gray))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Register",
                style = Typography.titleSmall.copy(color = Color(0xFF55a3ff), fontFamily = FontFamily(Font(R.font.inter_regular))),
                modifier = Modifier.clickable {

                }
            )
        }
    }
}
