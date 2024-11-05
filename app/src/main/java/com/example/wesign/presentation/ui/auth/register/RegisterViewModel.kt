package com.example.wesign.presentation.ui.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wesign.data.model.request.GenerateOtpRequest
import com.example.wesign.domain.usecase.user.register_use_case.GenerateOtpUseCase
import com.example.wesign.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val generateOtpUseCase: GenerateOtpUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.generateOtp -> {
                launchGenerateOtpEvent(event.name, event.email, event.password, event.role)
            }

            else -> {}
        }
    }

    private fun launchGenerateOtpEvent(
        name: String,
        email: String,
        password: String,
        role: String
    ) {
        viewModelScope.launch {
            val loginRequest = GenerateOtpRequest(name, email, password, role)
            generateOtpUseCase(loginRequest).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            messageResult = "OTP generated successfully",
                            isLoading = false,
                            isOtpVerified = true,
                            email = email,
                            name = name,
                            password = password
                        )
                    }

                    is Resource.Error -> {
                        _state.value =
                            _state.value.copy(
                                error = result.message,
                                isLoading = false,
                                isOtpVerified = false,
                                email = email,
                                name = name,
                                password = password
                            )
                    }
                }
            }
        }
    }
}