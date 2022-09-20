package com.example.data.model

import com.example.domain.model.CharacterDomainModel


fun MyResponseDTO.toCharacterDomainModel() =
    CharacterDomainModel(this.gender,this.id,this.image,this.locationName,this.name,this.species,this.status)

fun List<MyResponseDTO>.toCharacterDomainModel() = this.map { it.toCharacterDomainModel() }