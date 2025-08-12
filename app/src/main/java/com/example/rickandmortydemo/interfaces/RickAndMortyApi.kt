package com.example.rickandmortydemo.interfaces

import com.example.rickandmortydemo.dto.Character
import com.example.rickandmortydemo.dto.CharactersResponse

interface RickAndMortyApi {
    suspend fun getCharacters(page: Int = 1): CharactersResponse
    suspend fun getCharacter(id: Int): Character
}