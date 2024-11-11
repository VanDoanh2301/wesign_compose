package com.example.wesign.presentation.ui.main.test

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wesign.domain.usecase.study.getallquestion_use_case.GetAllQuestionUseCase
import com.example.wesign.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel  @Inject constructor(
    private val getAllQuestionsByClassroomIdUseCase: GetAllQuestionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TestScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: TestScreenEvent) {
        when (event) {
            is TestScreenEvent.GetQuestionByClassRoomId -> {
                launchGetQuestionByClassRoomId(event.classRoomId)
            }
            is TestScreenEvent.CheckAnswerCorrect -> {
                launchCheckAnswerCorrect(event.answer, event.page)
            }
            else -> {}
        }
    }

    private fun launchCheckAnswerCorrect(answer: String, page: Int) {
        val updatedQuestionState =
            state.value.listTestQuestions.mapIndexed { index, questionState ->
                if (index == page) {
                    if (questionState.answerCorrect == answer) {
                        _state.value = _state.value.copy(countCorrect = _state.value.countCorrect + 1)
                    }
                    questionState.copy(answerUser = answer)
                } else {
                    questionState
                }
            }
        _state.value = _state.value.copy(listTestQuestions = updatedQuestionState)
    }

    private fun launchGetQuestionByClassRoomId(classRoomId: Int) = viewModelScope.launch {
        getAllQuestionsByClassroomIdUseCase(classRoomId).collect {result->
            when(result) {
                is Resource.Success -> {
                    result.dataResource.let { listQuestion ->
                        _state.value = _state.value.copy(
                            listTestQuestions = listQuestion!!.map { question ->
                                QuestionState(question = question, answers = question.answers, answerCorrect = question.answers.find { it.correct }?.content ?: "")
                            }, isLoading = false, totalQuestion = listQuestion.size
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