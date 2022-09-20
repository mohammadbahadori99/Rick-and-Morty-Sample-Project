package com.example.data.repositry

import com.example.base.MyResponse
import com.example.data.LocalDataSource
import com.example.data.RemoteDataSource
import com.example.data.model.MyResponseDTO
import com.example.data.model.toCharacterDomainModel
import com.example.domain.model.CharacterDomainModel
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : CharactersRepository {
    override suspend fun fetchCharacters(): Flow<MyResponse<List<CharacterDomainModel>>> {
        val emptyResponse = emptyFlow<MyResponse<List<CharacterDomainModel>>>()
        val localData = localDataSource.getAllCharacters()
        return if (localData.isNotEmpty()) {
            flowOf(MyResponse.Success(localData.toCharacterDomainModel()))
        } else {
            handleRemoteData(remoteDataSource.fetchCharacters()) ?: emptyResponse
        }
    }

    private suspend fun handleRemoteData(remoteData: MyResponse<List<MyResponseDTO>>): Flow<MyResponse<List<CharacterDomainModel>>>? {
        return when (remoteData) {
            is MyResponse.Success -> {
                localDataSource.insertAllCharacters(remoteData.data)
                flowOf(
                    MyResponse.Success(
                        data = localDataSource.getAllCharacters().toCharacterDomainModel()
                    )
                )
            }
            is MyResponse.Error -> {
                flowOf(MyResponse.Error(remoteData.error))
            }
            else -> {
                null
            }
        }
    }
}