package com.example.wesign.presentation.ui.main.lesson

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wesign.domain.usecase.study.getalllesson_use_case.GetAllLessonUseCase
import com.example.wesign.domain.usecase.study.getallpart_use_case.GetAllPartUseCase
import com.example.wesign.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val getAllLessonUseCase: GetAllLessonUseCase,
    private val getAllPartUseCase: GetAllPartUseCase
) : ViewModel() {
    private var _state = MutableStateFlow(LessonScreenState())
    val state = _state.asStateFlow()
    fun onEvent(event: LessonScreenEvent) {
        when (event) {
            is LessonScreenEvent.GetLessonByClassId -> {
                launchListLesson(event.classId)
            }

            else -> {}

        }

    }

    private fun launchListLesson(classId: Int) = viewModelScope.launch {
        getAllLessonUseCase.invoke(classId).collectLatest {
            when (it) {
                is Resource.Success -> {
                    val listLessonPart = mutableListOf<LessonPartState>()
                     it.dataResource?.map { lesson ->
                        getAllPartUseCase.invoke(lesson.lessonId).collectLatest { partResult ->
                            when (partResult) {
                                is Resource.Success -> {
                                    val listPart = partResult.dataResource!!
                                    listLessonPart.add(LessonPartState(lesson, listPart))

                                }

                                is Resource.Error -> {

                                }

                                is Resource.Loading -> {

                                }
                            }
                        }
                    }
                    _state.value = _state.value.copy(listLesson = listLessonPart, isLoading = false)
                }

                is Resource.Error -> {
                    _state.value =
                        _state.value.copy(error = it.message.toString(), isLoading = false)
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }
    }
}