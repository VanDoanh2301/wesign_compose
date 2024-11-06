package com.example.wesign.presentation.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.UserDetail
import com.example.wesign.domain.usecase.study.getallclassrooms_use_case.GetAllClassroomsUseCase
import com.example.wesign.domain.usecase.study.getallvocabularies_use_case.GetAllVocabulariesUseCase
import com.example.wesign.domain.usecase.user.user_detail_use_case.GetUserDetailUseCase
import com.example.wesign.utils.DataPreferences
import com.example.wesign.utils.Resource
import com.example.wesign.utils.USER_DETAIL_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val getAllClassRoomsUseCase: GetAllClassroomsUseCase,
    private val getAllVocabulariesUseCase: GetAllVocabulariesUseCase,
    private val dataPreferences: DataPreferences
) :
    ViewModel() {
    private val _userDetailState = MutableStateFlow(UserDetailState())
    val userDetailState = _userDetailState.asStateFlow()

    private val _vocabularyState = MutableStateFlow(VocabularyState())
    val vocabularyState = _vocabularyState.asStateFlow()

    private var _classRoomState = MutableStateFlow<PagingData<ClassRoom>>(PagingData.empty())
    val classRoomState  =_classRoomState.asStateFlow()



    init {
        viewModelScope.launch {
            dataPreferences.getDecryptedString(USER_DETAIL_KEY, UserDetail::class.java).collect {
                if (it != null) {
                    _userDetailState.value = _userDetailState.value.copy(userDetail = it )
                } else {
                    getUserDetail()
                }
            }
        }


    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.GetUserDetail -> {
                getUserDetail()
            }

            is HomeScreenEvent.GetAllClassRooms -> {
                getAllClassRooms()
            }

            is HomeScreenEvent.GetAllVocabularies -> {
                getAllVocabularies()
            }

            else -> {}
        }
    }

    private fun getAllClassRooms() {
        viewModelScope.launch {
           getAllClassRoomsUseCase.invoke().cachedIn(viewModelScope).collect {
               _classRoomState.value = it
           }
        }
    }

    private fun getAllVocabularies() = viewModelScope.launch {
        _vocabularyState.value = _vocabularyState.value.copy(isLoading = true)
        getAllVocabulariesUseCase().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _vocabularyState.value = _vocabularyState.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    _vocabularyState.value = _vocabularyState.value.copy(
                        vocabularies = result.dataResource?.data ?: emptyList(), isLoading = false
                    )
                }

                is Resource.Error -> {
                    _vocabularyState.value =
                        _vocabularyState.value.copy(error = result.message, isLoading = false)
                }
            }
        }
    }

    private fun getUserDetail() = viewModelScope.launch {
        getUserDetailUseCase().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _userDetailState.value = _userDetailState.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    dataPreferences.saveEncryptedString(
                        USER_DETAIL_KEY,
                        result.dataResource?.data!!
                    )
                    _userDetailState.value = _userDetailState.value.copy(
                        userDetail = result.dataResource?.data,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _userDetailState.value =
                        _userDetailState.value.copy(error = result.message, isLoading = false)
                }
            }

        }
    }
}