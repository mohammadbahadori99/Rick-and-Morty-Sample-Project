package com.example.data.datasource.model

import com.example.data.model.MyResponseDTO

fun Result.toMyResponseDTO() = MyResponseDTO(
    this.created,
    this.episode,
    this.gender,
    this.id,
    this.image,
    this.location.name,
    this.location.url,
    this.name,
    this.origin.name,
    this.origin.url,
    this.species,
    this.status,
    this.type,
    this.url
)

fun List<Result>.toMyResponseDTO() = this.map { it.toMyResponseDTO() }

fun MyResponseDTO.toMyResponseEntity() = MyResponseEntity(
    this.created,
    this.episode,
    this.gender,
    this.id,
    this.image,
    Location(this.locationName, this.locationUrl),
    this.name,
    Origin(this.originName, this.originUrl),
    this.species,
    this.status,
    this.type,
    this.url
)

fun MyResponseEntity.toMyResponseDTO() = MyResponseDTO(
    this.created,
    this.episode,
    this.gender,
    this.id,
    this.image,
    this.location.name, this.location.url,
    this.name,
    this.origin.name, this.origin.url,
    this.species,
    this.status,
    this.type,
    this.url
)