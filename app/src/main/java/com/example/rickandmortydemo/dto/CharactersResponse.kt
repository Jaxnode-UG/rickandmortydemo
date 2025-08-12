package com.example.rickandmortydemo.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: PageInfo,
    val results: List<Character>
)