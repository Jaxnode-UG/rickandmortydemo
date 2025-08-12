package com.example.rickandmortydemo.interfaces

import com.example.rickandmortydemo.dto.Character

sealed interface CharacterDetailUiState {
    object Loading : CharacterDetailUiState
    data class Data(val character: Character) : CharacterDetailUiState
    data class Error(val message: String) : CharacterDetailUiState
}