package com.example.domain.repository

import com.example.base.MyResponse
import com.example.domain.model.CharacterDomainModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun fetchCharacters(): Flow<MyResponse<List<CharacterDomainModel>>>
}