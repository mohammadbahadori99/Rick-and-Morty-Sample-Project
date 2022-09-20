package com.example.data.datasource.local

import com.example.data.LocalDataSource
import com.example.data.datasource.model.toMyResponseDTO
import com.example.data.datasource.model.toMyResponseEntity
import com.example.data.model.MyResponseDTO
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: CharacterDao) : LocalDataSource {
    override suspend fun getAllCharacters(): List<MyResponseDTO> {
        return dao.getAllCharacters().map { it.toMyResponseDTO() }

    }

    override suspend fun insertAllCharacters(characterList: List<MyResponseDTO>) {
        dao.insertAllCharacters(characterList.map { it.toMyResponseEntity() })
    }
}