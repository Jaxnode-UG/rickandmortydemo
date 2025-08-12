package com.example.rickandmortydemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortydemo.CharacterRepository
import com.example.rickandmortydemo.dto.CharactersUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(private val repo: CharacterRepository = CharacterRepository()) : ViewModel() {
    private val _state = MutableStateFlow(CharactersUiState())
    val state = _state.asStateFlow()

    init { refresh() }

    fun refresh(page: Int = 1) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            runCatching { repo.list(page) }
                .onSuccess { resp ->
                    _state.value = CharactersUiState(
                        loading = false,
                        characters = resp.results,
                        page = page,
                        hasNext = resp.info.next != null
                    )
                }
                .onFailure { e -> _state.value = _state.value.copy(loading = false, error = e.message) }
        }
    }

    fun nextPage() { if (state.value.hasNext) refresh(state.value.page + 1) }
}