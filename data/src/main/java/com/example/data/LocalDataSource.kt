package com.example.data

import com.example.data.model.MyResponseDTO


interface LocalDataSource {
    suspend fun getAllCharacters(): List<MyResponseDTO>

    suspend fun insertAllCharacters(characterList: List<MyResponseDTO>)
}