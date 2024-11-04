package com.example.wesign.presentation.ui.auth.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wesign.data.model.request.GenerateOtpRequest
import com.example.wesign.data.model.request.ValidateOtpRequest
import com.example.wesign.domain.usecase.user.otp_use_case.ValidateOtpUseCase
import com.example.wesign.domain.usecase.user.register_use_case.GenerateOtpUseCase
import com.example.wesign.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class OtpViewModel @Inject constructor(
    private val validateOtpUseCase: ValidateOtpUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(OtpScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: OtpScreenEvent) {
        when (event) {
            is OtpScreenEvent.validateOtp -> {
                launchValidateOtp(event.email, event.otp)
            }
            else -> {}
        }

    }

    private fun launchValidateOtp(email: String, otp: String) {
        viewModelScope.launch {
            val otpRequest = ValidateOtpRequest(email, otp)
            validateOtpUseCase.invoke(otpRequest).collect {result->
                when(result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(messageResult = "OTP verified successfully", isLoading = false, isOtpVerified = true)
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(messageResult = result.message, isLoading = false, isOtpVerified = false)
                    }

                }
            }
        }
    }
}