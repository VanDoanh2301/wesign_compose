package com.example.wesign.presentation.ui.main.exam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wesign.data.model.request.ExamRequest
import com.example.wesign.domain.usecase.study.getallexam_use_case.GetAllExamUseCase
import com.example.wesign.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamViewModel @Inject constructor(private val getAllExamUseCase: GetAllExamUseCase) : ViewModel() {
    private val _state = MutableStateFlow(ExamScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: ExamScreenEvent) {
        when (event) {
            is ExamScreenEvent.GetAllExam -> {
                launchGetAllExam()
            }
            else -> {}
        }
    }

    private fun launchGetAllExam() = viewModelScope.launch {
        getAllExamUseCase(ExamRequest(
            classRoomId = 0,
            isPrivate = "",
            nameSearch = "",
            page = 0,
            size =100,
            ascending = "",
            orderBy = ""
        )).collect { result ->
            when (result) {
                is Resource.Success -> {
                    result.dataResource.let { listExam ->
                        _state.value = _state.value.copy(
                            listExams = listExam!!, isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message ?: "Error desconocido", isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }
    }
}
