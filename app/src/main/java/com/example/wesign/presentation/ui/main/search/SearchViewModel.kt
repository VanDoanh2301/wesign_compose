package com.example.wesign.presentation.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Topic
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.usecase.study.getallclassrooms_use_case.GetAllClassroomsUseCase
import com.example.wesign.domain.usecase.study.getalltopic_use_case.GetAllTopicUseCase
import com.example.wesign.domain.usecase.study.getallvocabularies_use_case.GetAllVocabulariesUseCase
import com.example.wesign.presentation.ui.main.home.home_page.components.TypeSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllVocabulariesUseCase: GetAllVocabulariesUseCase,
    private val getAllTopicUseCase: GetAllTopicUseCase,
    private val getAllClassroomsUseCase: GetAllClassroomsUseCase
) : ViewModel(){

    private val _vocabularyState = MutableStateFlow<PagingData<Vocabulary>>(PagingData.empty())
    val vocabularyState = _vocabularyState.asStateFlow()

    private var _classRoomState = MutableStateFlow<PagingData<ClassRoom>>(PagingData.empty())
    val classRoomState  =_classRoomState.asStateFlow()

    private val _topicState = MutableStateFlow<PagingData<Topic>>(PagingData.empty())
    val topicState = _topicState.asStateFlow()

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnSearchQueryChange -> {
                when (event.typeSearch) {
                    TypeSearch.VOCABULARY.title -> {
                        getAllVocabularies(event.query)
                    }
                    TypeSearch.CLASSROOM.title -> {
                        getAllClassRooms(event.query)
                    }
                    TypeSearch.TOPIC.title -> {
                        getAllTopics(event.query)
                    }
                }
            }
            else -> {}
        }
    }

    private fun getAllVocabularies(query: String) = viewModelScope.launch {
        getAllVocabulariesUseCase.invoke(query = query).cachedIn(viewModelScope).collect {
            _vocabularyState.value = it
        }
    }
    private fun getAllTopics(query: String) {
        viewModelScope.launch {
            getAllTopicUseCase.invoke(query = query).cachedIn(viewModelScope).collect {
                _topicState.value = it
            }
        }
    }
    private fun getAllClassRooms(query: String) {
        viewModelScope.launch {
            getAllClassroomsUseCase.invoke(query = query).cachedIn(viewModelScope).collect {
                _classRoomState.value = it
            }
        }
    }
}