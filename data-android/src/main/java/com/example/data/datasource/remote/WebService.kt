package com.example.data.datasource.remote


import com.example.data.datasource.model.ServerResponseModel

interface WebService {
    @retrofit2.http.GET("/api/character")
    suspend fun fetchCharacters(): ServerResponseModel
}