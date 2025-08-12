package com.example.rickandmortydemo.dto

import kotlinx.serialization.Serializable
@Serializable
data class NamedRef(
    val name: String,
    val url: String
)