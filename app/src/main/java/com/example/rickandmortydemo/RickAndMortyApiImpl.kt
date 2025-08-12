package com.example.rickandmortydemo

import com.example.rickandmortydemo.dto.Character
import com.example.rickandmortydemo.dto.CharactersResponse
import com.example.rickandmortydemo.interfaces.RickAndMortyApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// -----------------------------------------
// API layer (Ktor)
// -----------------------------------------
private const val BASE_URL = "https://rickandmortyapi.com/api"

object KtorClientHolder {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }
}
class RickAndMortyApiImpl : RickAndMortyApi {
    private val client = KtorClientHolder.client

    override suspend fun getCharacters(page: Int): CharactersResponse {
        return client.get("$BASE_URL/character?page=$page").body()
    }

    override suspend fun getCharacter(id: Int): Character {
        return client.get("$BASE_URL/character/$id").body()
    }
}