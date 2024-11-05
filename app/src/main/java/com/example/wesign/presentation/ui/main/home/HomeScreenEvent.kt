package com.example.wesign.presentation.ui.main.home

import com.example.wesign.domain.model.UserDetail

sealed class HomeScreenEvent {
    data object getUserDetail : HomeScreenEvent()
}

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val userDetail: UserDetail? = null,
    val error: String? = null
)