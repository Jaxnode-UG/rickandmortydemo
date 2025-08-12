package com.example.rickandmortydemo

import com.example.rickandmortydemo.interfaces.RickAndMortyApi

// -----------------------------------------
// Repository (simple)
// -----------------------------------------
class CharacterRepository(private val api: RickAndMortyApi = RickAndMortyApiImpl()) {
    suspend fun list(page: Int = 1) = api.getCharacters(page)
    suspend fun get(id: Int) = api.getCharacter(id)
}