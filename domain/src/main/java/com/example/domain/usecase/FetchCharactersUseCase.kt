package com.example.domain.usecase

import com.example.base.MyResponse
import com.example.domain.model.CharacterDomainModel
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(): Flow<MyResponse<List<CharacterDomainModel>>> {
        return charactersRepository.fetchCharacters()
    }
}