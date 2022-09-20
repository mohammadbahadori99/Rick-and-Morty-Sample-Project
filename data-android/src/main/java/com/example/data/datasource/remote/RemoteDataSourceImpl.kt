package com.example.data.datasource.remote

import com.example.base.MyResponse
import com.example.data.RemoteDataSource
import com.example.data.datasource.model.toMyResponseDTO
import com.example.data.model.MyResponseDTO
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val webService: WebService) :
    RemoteDataSource {
    override suspend fun fetchCharacters(): MyResponse<List<MyResponseDTO>> {
        return try {
            val data = webService.fetchCharacters()
            MyResponse.Success(data.results.toMyResponseDTO())
        } catch (e: Exception) {
            MyResponse.Error(e)
        }
    }
}