package com.example.wesign.presentation.ui.main.search

import com.example.wesign.presentation.ui.main.home.home_page.components.TypeSearch

sealed class SearchScreenEvent {
    data class OnSearchQueryChange(val query: String, val typeSearch: String) : SearchScreenEvent()
}