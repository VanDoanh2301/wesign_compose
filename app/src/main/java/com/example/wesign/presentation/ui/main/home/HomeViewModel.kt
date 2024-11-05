package com.example.wesign.presentation.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wesign.domain.model.UserDetail
import com.example.wesign.domain.usecase.user.user_detail_use_case.GetUserDetailUseCase
import com.example.wesign.utils.DataPreferences
import com.example.wesign.utils.Resource
import com.example.wesign.utils.USER_DETAIL_KEY
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val dataPreferences: DataPreferences
) :
    ViewModel() {
    private val _state = MutableStateFlow(HomeScreenUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataPreferences.getDecryptedString(USER_DETAIL_KEY, UserDetail::class.java).collect {
                if (it != null) {
                    _state.value = _state.value.copy(userDetail = it)
                } else {
                    getUserDetail()
                }
            }
        }
    }

    private fun getUserDetail() = viewModelScope.launch {
        getUserDetailUseCase().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                   dataPreferences.saveEncryptedString(USER_DETAIL_KEY, result.dataResource?.data!!)
                    _state.value = _state.value.copy(userDetail = result.dataResource?.data, isLoading = false)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message, isLoading = false)
                }
            }

        }
    }
}