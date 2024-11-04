package com.example.wesign.domain.usecase.user.login_use_case

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wesign.data.model.request.LoginRequest
import com.example.wesign.utils.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LoginUseCaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        hiltRule.inject()
    }


    @Test
    fun testLoginUseCase() = runBlocking {
        loginUseCase.invoke(
            LoginRequest(
                "1234567890@gmail.com",
                "123456"
            )
        ).collect {
            when (it) {
                is Resource.Success -> {
                    println(it.data)
                }

                is Resource.Error -> {
                    Log.e("Test Login", "Login failed: ${it.message}")
                }

                is Resource.Loading -> {
                    println("Status Loading")
                }
            }
        }

    }
}