package com.example.rickandmortydemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortydemo.CharacterRepository
import com.example.rickandmortydemo.interfaces.CharacterDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val id: Int,
    private val repo: CharacterRepository = CharacterRepository()
) : ViewModel() {
    private val _state = MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching { repo.get(id) }
                .onSuccess { _state.value = CharacterDetailUiState.Data(it) }
                .onFailure { _state.value = CharacterDetailUiState.Error(it.message ?: "Unknown error") }
        }
    }
}