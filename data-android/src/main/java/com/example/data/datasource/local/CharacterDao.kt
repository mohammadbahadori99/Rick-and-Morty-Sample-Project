package com.example.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.datasource.model.MyResponseEntity


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(list: List<MyResponseEntity>)

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<MyResponseEntity>

}