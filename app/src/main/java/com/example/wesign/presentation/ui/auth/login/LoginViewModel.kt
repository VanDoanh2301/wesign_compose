package com.example.wesign.presentation.ui.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wesign.data.model.request.LoginRequest
import com.example.wesign.domain.usecase.user.login_use_case.LoginUseCase
import com.example.wesign.utils.DataPreferences
import com.example.wesign.utils.Resource
import com.example.wesign.utils.TOKEN_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataPreferences: DataPreferences
) :ViewModel() {
    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()


    fun onEvent(event:LoginScreenEvent){
        when(event) {
            is LoginScreenEvent.onLogin -> {
                launchLoginUseCase(event.email, event.password)
            }
            else -> {}
        }
    }

    private fun launchLoginUseCase(email: String, password: String) = viewModelScope.launch {
        val loginRequest = LoginRequest(email, password)
        loginUseCase(loginRequest).collect {result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    val accessToken = result.dataResource?.data?.accessToken
                    dataPreferences.saveEncryptedString(TOKEN_KEY, accessToken!!)
                    _state.value = _state.value.copy(isSuccessful = true, isLoading = false)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(isSuccessful = false, isLoading = false, error = result.message)
                }

            }
        }

    }
}