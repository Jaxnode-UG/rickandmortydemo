package com.example.rickandmortydemo.dto

data class CharactersUiState(
    val loading: Boolean = false,
    val characters: List<com.example.rickandmortydemo.dto.Character> = emptyList(),
    val page: Int = 1,
    val hasNext: Boolean = false,
    val error: String? = null
)