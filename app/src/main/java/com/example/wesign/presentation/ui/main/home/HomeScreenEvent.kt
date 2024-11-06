package com.example.wesign.presentation.ui.main.home

import androidx.paging.PagingData
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.UserDetail
import com.example.wesign.domain.model.Vocabulary
import kotlinx.coroutines.flow.StateFlow

sealed class HomeScreenEvent {
    data object GetUserDetail : HomeScreenEvent()
    data object GetAllClassRooms : HomeScreenEvent()
    data object GetAllVocabularies : HomeScreenEvent()
}

data class UserDetailState(
    val isLoading: Boolean = false,
    val userDetail: UserDetail? = null,
    val error: String? = null
)
